package com.niit.mobineer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.niit.mobineer.dao.CategoryDAO;
import com.niit.mobineer.dao.ProductDAO;
import com.niit.mobineer.domain.Category;
import com.niit.mobineer.domain.Product;

@Controller

public class AdminController 
{
	@Autowired CategoryDAO categoryDAO;
	@Autowired Category category;

	
	@Autowired ProductDAO productDAO;
	@Autowired Product product;
	
	@Autowired HttpSession session;
	
	
	@RequestMapping("/manageCategories")
	public ModelAndView manageCategories()
	{
		
		ModelAndView mv = new ModelAndView("admin/AdminHome");
		mv.addObject("isAdminClickedCategories", "true");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		return mv;
		
	}

	@RequestMapping("/manageProducts")
	public ModelAndView manageProducts()
	{
		
		ModelAndView mv = new ModelAndView("admin/AdminHome");
		mv.addObject("isAdminClickedProducts", "true");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		return mv;
		
	}
	
	@RequestMapping("/logoutAdmin")
	public ModelAndView manageLogout()
	{
		ModelAndView mv = new ModelAndView("Home");
		 mv.addObject("isAdmin", "false");
		 return mv;
	}
}
