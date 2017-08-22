package com.niit.mobineer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import com.niit.mobineer.dao.UserDAO;
import com.niit.mobineer.domain.User;


@Controller
@SessionAttributes("user")
public class UserController {
	

	@Autowired User user;
	@Autowired UserDAO userDAO;
	
	@Autowired HttpSession session;
	
	@PostMapping("/validate")
	public ModelAndView login(@RequestParam("userName") String id, @RequestParam("password") String password)
	
	{
		

		// call validate method from UserDAO to validate the entered credentials
		 
		
		 ModelAndView mv = new ModelAndView("/Home");		
		 if(userDAO.validate(id, password)==true)								//calling validate method from UserDAO
		 {
			 
			 user = userDAO.getUserById(id);
			 
			 //${message}  - to display in the Home.jsp
			 mv.addObject("message", " Welcome To Mobineer " + user.getName());
			 
			
			 //check whether user is customer or administrator
			 
			 if(user.getRole().equals("ROLE_ADMIN"))							//checking whether the user is customer or Admin
			 {
				 mv = new ModelAndView("admin/AdminHome");
				 mv.addObject("isAdmin", "true");
			 }
			 else
			 {

				 mv.addObject("isAdmin", "false");
			 }
			 
			 
		 }
		 else
		 {

			 mv.addObject("message", "Invalid credentials..please try again.");
		 }
		 
		 return mv;
		
	}

}

