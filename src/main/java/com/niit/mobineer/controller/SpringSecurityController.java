package com.niit.mobineer.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.mobineer.dao.*;
import com.niit.mobineer.domain.*;


@Controller
public class SpringSecurityController {

	public static Logger log = LoggerFactory.getLogger(SpringSecurityController.class);

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Cart cart;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private CartItemDAO cartItemDAO;
	

	@Autowired
	private Product product;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	
	// authentication-failure-forward-url="/loginError"
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		log.debug("Starting of the method loginError");
		model.addAttribute("errorMessage", "Invalid Credentials.  Please try again.");
		//model.addAttribute("invalidCredentials", "true");
		log.debug("Ending of the method loginError");
		return "home";

	}

	// <security:access-denied-handler error-page="/accessDenied" />
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		log.debug("Starting of the method accessDenied");
		model.addAttribute("errorMessage", "You are not authorized to access this page");

		log.debug("Ending of the method accessDenied");
		return "Home";

	}
	// <security:form-login authentication-success-forward-url="/success"/>
	/*
	 * @RequestMapping("/success") public ModelAndView successForwardURL() {
	 * log.debug("Starting of the method successForwardURL"); ModelAndView mv =
	 * new ModelAndView("home");
	 * 
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	 * String loggedInUserid = auth.getName(); 
	 * Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities(); 
	 * if(authorities.contains("ROLE_ADMIN")) 
	 * { mv.addObject("isAdmin", "true");
	 * log.debug("You are Admin"); } 
	 * else 
	 * { log.debug("You are not  Admin");
	 * mv.addObject("isAdmin", "false"); // myCart = cartDAO.list(userID);
	 * mv.addObject("myCart", myCart); // Fetch the myCart list based on user ID
	 * List<MyCart> cartList = cartDAO.list(loggedInUserid);
	 * mv.addObject("cartList", cartList); mv.addObject("cartSize",
	 * cartList.size()); }
	 * 
	 * log.debug("Ending of the method successForwardURL"); return mv;
	 * 
	 * }
	 */
/**
 * If we are using spring-security, this method is going to execute after login
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
/*  @RequestMapping(value = "/validate", method = RequestMethod.GET)
	//@RequestMapping(value = "/checkRole", method = RequestMethod.GET)
	public ModelAndView checkRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("starting of the method validate");
		ModelAndView mv = new ModelAndView("Home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String uID = auth.getName();
		String userID = uID;
		session.setAttribute("loggedInUser", userID);

		if (request.isUserInRole("ADMIN")) {

			session.setAttribute("isAdmin", true);

		} else {

			session.setAttribute("isAdmin", false);
			
			session.setAttribute("Cart", cart);
			// Fetch the myCart list based on user ID
			User user=userDAO.getUserById(userID);
			cart=user.getCart();
			CartItem cartItem=(CartItem) cart.getCartList();
		//	List<CartItem> cartItemList = cartItemDAO.list(userID);
		//	session.setAttribute("cartItemList", cartItemList);
			session.setAttribute("cartSize", cart.getCartItemCount());
			session.setAttribute("totalAmount", cart.getGrandTotal());

			

		}
		log.debug("Ending of the method validate");
		return mv;
	}
*/
	
	@RequestMapping("/secure_logout")
	public ModelAndView secureLogout()
	{
		//what you attach to session at the time login need to remove.
		
		//session.removeAttribute("loggedInUserID");
		session.invalidate();
		
		ModelAndView mv = new ModelAndView("Home");
		
		//After logout also use should able to browse the categories and products
		//as we invalidated the session, need to load these data again.
		
		session.setAttribute("category", category); // domain object names
		session.setAttribute("product", product);
		
		
		session.setAttribute("categoryList", categoryDAO.list());
		
		
		session.setAttribute("productList", productDAO.list());
		
		
		//OR Simpley remove only one attribute from the session.
		
		//session.removeAttribute("loggedInUser"); // you no need to load categories,suppliers and products
	
		return mv;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
