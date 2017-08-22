
package com.niit.mobineer.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.niit.mobineer.dao.CategoryDAO;
import com.niit.mobineer.domain.Category;

@Controller

public class CategoryController {

	// create category
	// update category
	// delete category
	// fetch all categories
	
	private static Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired CategoryDAO categoryDAO;
	
	@Autowired Category category;
	
	@Autowired HttpSession session;
	
	/*--------------------------- Create Category ----------------------------------*/
	
	@RequestMapping("/manage_category_add")
	public ModelAndView createCategory(@RequestParam("id") String id, @RequestParam("name") String name)
	{
		log.debug("Starting of the method createCategory");
		
		category.setId(id);
		category.setName(name);
		
		ModelAndView mv = new ModelAndView("admin/AdminHome");
		
		mv.addObject("isAdminClickedCategories","true");
		mv.addObject("isAdmin","true");
		
		/*
		 *  Before calling the save method check whether save method is exist in db or not
		 *  if it is does not exist, then only call save method
		 */
		
		
		if (categoryDAO.getCategoryById(id) != null) //check whether category already exist or not
		{

			category=categoryDAO.getCategoryById(id);
			if(category.isEnabled()==false)						// category is disabled
			{
				categoryDAO.undelete(category);					// category is enabled now
				mv.addObject("message","Category Already Exist with the ID "+id+ " but was disabled, now this Category has been enabled so you can edit the Category.");
				
			}
			else if(category.isEnabled()==true)					// category already exist
			{
				
				
				mv.addObject("message","Category Already Exist with the ID "+id);
				return mv;
			}
		}
		else 										//actually else is not required if return statement is there in if condition
		{
			categoryDAO.save(category);
			mv.addObject("message","Category Created Successfully");
		}
		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		
		return mv;
	}
	
	
	/*------------------------------------- Delete Category -------------------------------*/

	@RequestMapping("/manage_category_delete/{id}")				// attached to url with path variable
	public ModelAndView deleteCategory(@PathVariable("id") String id)
	{
		
		ModelAndView mv = new ModelAndView("redirect:/manageCategories");
		

		mv.addObject("isAdminClickedCategories","true");
		mv.addObject("isAdmin","true");
		
		
		if (categoryDAO.delete(categoryDAO.getCategoryById(id))==true) 
		{
			mv.addObject("message"," Successfully deleted the category");
		}
		else 	
		{
			mv.addObject("message","Not able to Delete the Category");
		}
		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
				
		return mv;
	}
	
	/*------------------------------------Edit Category -----------------------------------*/
	

	@RequestMapping("/manage_category_edit/{id}")
	public ModelAndView editCategory(@PathVariable("id") String id)
	{
		log.debug("Starting of method Edit Categories");
		
		log.debug("Going to Edit the Category : "+id);
		
		category = categoryDAO.getCategoryById(id);
		
		/*
		 * selected category details stored in another instance
		 * in ModelAndView instance
		 */
		
		ModelAndView mv = new ModelAndView("forward:/manageCategories");
		
		mv.addObject("selectedCategory",category);
		mv.addObject("isAdmin","true");
		
		log.debug("Ending of the method Edit Category");
		
		return mv;
	}
	
	
	/*-----------------------------------Update Category ------------------------------------------*/
	
	
	@RequestMapping("/manage_category_edit/manage_category_update")
	public ModelAndView updateCategory(@RequestParam("id") String id, @RequestParam("name") String name)
	{
		log.debug("Starting of the method updateCategory");
		
		log.debug("Going to edit the category : "+id);
		
		category.setId(id);
		category.setName(name);
		
		ModelAndView mv = new ModelAndView("redirect:/manageCategories");
		
		mv.addObject("isAdminClickedCategories","true");
		mv.addObject("isAdmin","true");
		
		/* 
		 * Before calling the update method check whether id  is exist in db or not
		 * if it is  exist, then only call update method
		 */ 
		
		if (categoryDAO.getCategoryById(id) == null) 
		{

			
			mv.addObject("message","Category does not Exist with the ID "+id);			// category does not exist
			return mv;
		}
		else 											//actually else is not required if return statement is there in if condition
		{
			categoryDAO.update(category);
			mv.addObject("message","Category Updated Successfully with id :"+id);
			log.debug("Category Updated Successfully");
		}
		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		
		log.debug("Ending of the method  updateCategory");
		
		return mv;
	}
	
	
}





