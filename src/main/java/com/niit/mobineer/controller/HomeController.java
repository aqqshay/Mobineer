package com.niit.mobineer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.niit.mobineer.dao.CategoryDAO;
import com.niit.mobineer.dao.ProductDAO;
import com.niit.mobineer.domain.Category;
import com.niit.mobineer.domain.Product;
import com.niit.mobineer.domain.User;

@Controller
@SessionAttributes("user")
public class HomeController {
	
	@Autowired CategoryDAO categoryDAO;
	@Autowired Category category;

	
	@Autowired ProductDAO productDAO;
	@Autowired Product product;
	@Autowired User user;
	@Autowired HttpSession session;
	//http://localhost:8080/ShoppingCart/
	
	
	
	@RequestMapping("/")
	public  String    goToHome(Model model)
	
	{
		model.addAttribute("message", "Shopping Website");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		session.setAttribute("user", user.getId());
		return "Home";
	}
	
	
	@RequestMapping("/LoginPage")
	public String loginPage(Model model)
	{
		model.addAttribute("message", "Please enter your details to login");
		model.addAttribute("isUserClickedLogin", "true");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		
		return "Home";
	}
	
	@RequestMapping("/RegistrationPage")
	public String registrationPage(Model model)
	{	
		model.addAttribute("message", "Please enter the details for registration");
		model.addAttribute("isUserClickedRegister", "true");
		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("category", category);
		session.setAttribute("productList", productDAO.list());
		session.setAttribute("product", product);
		
		return "Home";
	}


}