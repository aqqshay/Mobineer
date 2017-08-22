
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
import com.niit.mobineer.dao.ProductDAO;
import com.niit.mobineer.domain.Category;
import com.niit.mobineer.domain.Product;

@Controller
public class ProductController {

	// create product
	// update product
	// delete product
	// fetch all products
	// fetch all categories
	
	
	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired ProductDAO productDAO;
	
	@Autowired Product product;
	
	@Autowired CategoryDAO categoryDAO;
	
	@Autowired Category category;
	
	@Autowired HttpSession session;
	
	/*--------------------------- Create Product ----------------------------------*/
	
	@RequestMapping("/manage_product_add")
	public ModelAndView createProduct(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("description") String description, @RequestParam("category_id") String category_id)
	{
		log.debug("Starting of the method createProduct");
		category= categoryDAO.getCategoryById(category_id);
		category.setId(category_id);
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setCategory(category);
		
		
		ModelAndView mv = new ModelAndView("admin/AdminHome");
		
		mv.addObject("isAdminClickedProducts","true");
		mv.addObject("isAdmin","true");

		/*
		 *  Before calling the save method check whether save method is exist in db or not
		 *  if it is does not exist, then only call save method
		 */
		
		
		if (productDAO.getProductById(id) != null) //check whether product already exist or not
		{

			product=productDAO.getProductById(id);
			if(product.isEnabled()==false)						// product is disabled
			{
				productDAO.undelete(product);					// product is enabled now
				mv.addObject("message","Product Already Exist with the id "+id+ " but was disabled, now this Product has been enabled so you can edit the Product.");
				
			}
			else if(product.isEnabled()==true)					// product already exist
			{
				
				
				mv.addObject("message","Product Already Exist with the id "+id);
				return mv;
			}
		}
		else 										//actually else is not required if return statement is there in if condition
		{
			
			productDAO.save(product);
			mv.addObject("message","Product Created Successfully");
		}

		
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		
		return mv;
	}
	
	
	/*------------------------------------- Delete Product -------------------------------*/

	@RequestMapping("/manage_product_delete/{id}")				// attached to url with path variable
	public ModelAndView deleteProduct(@PathVariable("id") String id)
	{
		
		ModelAndView mv = new ModelAndView("redirect:/manageProducts");
		
		mv.addObject("message", "SuccessFully Deleted Product");
		mv.addObject("isAdminClickedProducts","true");
		mv.addObject("isAdmin","true");
		
		
		if (productDAO.delete(productDAO.getProductById(id))==true) 
		{
			mv.addObject("message"," Successfully deleted the product");
		}
		else 	
		{
			mv.addObject("message","Not able to Delete the Product");
		}
		

		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		
		log.debug("Ending of the method delete Product");
		
		return mv;
	}
	
	/*------------------------------------Edit Product -----------------------------------*/
	

	@RequestMapping("/manage_product_edit/{id}")
	public ModelAndView editProduct(@PathVariable("id") String id)
	{
		log.debug("Starting of method Edit Products");
		
		log.debug("Going to Edit the Product : "+id);
		
		product = productDAO.getProductById(id);
		
		/*
		 * selected product details stored in another instance
		 * in ModelAndView instance
		 */
		
		ModelAndView mv = new ModelAndView("forward:/manageProducts");
		

		mv.addObject("selectedProduct",product);
		mv.addObject("isAdmin","true");
		
		log.debug("Ending of the method Edit Product");
		
		return mv;
	}
	
	
	/*-----------------------------------Update Product ------------------------------------------*/
	
	
	@RequestMapping("/manage_product_edit/manage_product_update")
	public ModelAndView updateProduct(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("description") String description, @RequestParam("category_id") String category_id)
	{
		log.debug("Starting of the method updateProduct");
		
		log.debug("Going to edit the product : "+id);
		
		category=categoryDAO.getCategoryById(category_id);
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setCategory(category);
		ModelAndView mv = new ModelAndView("redirect:/manageProducts");
		
		mv.addObject("isAdminClickedProducts","true");
		mv.addObject("isAdmin","true");
		
		/* 
		 * Before calling the update method check whether id  is exist in db or not
		 * if it is  exist, then only call update method
		 */ 
		
		if (productDAO.getProductById(id) == null) 
		{
			log.debug("Product does not Exist with  in Database");
			
			mv.addObject("message","Product does not Exist with the id "+id);			// product does not exist
			return mv;
		}
		else 											//actually else is not required if return statement is there in if condition
		{
			productDAO.update(product);
			mv.addObject("message","Product Updated Successfully with id :"+id);
			log.debug("Product Updated Successfully");
		}
		

		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		
		log.debug("Ending of the method  updateProduct");
		
		return mv;
	}
	
	
}





