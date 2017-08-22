package com.niit.mobineer.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.niit.mobineer.dao.*;
import com.niit.mobineer.domain.*;


@Controller
public class OrderController 
{
	@Autowired
	private User user;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Cart cart;

	@Autowired
	private CartItem cartItem;
	
	@Autowired
	private CartItemDAO cartItemDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private OrderedItemsDAO orderedItemsDAO;

	@Autowired
	private Product product;

	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("/getOrderData")
	public String getOrderDetails(Model mv)
	{
		//System.out.println("inside ordercontroller");
		//mv.addObject("isUser", "true");
		int shipping;
		String loggedInUserid = ((String) session.getAttribute("userId"));// (long)

		user = userDAO.getUserById(loggedInUserid);
		cart = user.getCart();
		int cartSize = cart.getCartItemCount();
		if(cart.getGrandTotal()<500)
		{
			shipping=50;
		}
		else
		{
			shipping=0;
		}
		mv.addAttribute("cartList", cartItemDAO.cartItemGetByCart(cart));
		mv.addAttribute("totalAmount", cart.getGrandTotal());
		mv.addAttribute("isUserClickedCheckout", "true");
		mv.addAttribute("totalQty", cart.getCartItemCount());
		mv.addAttribute("shipping",shipping);
		return "/Home";
		
	}
	
	@RequestMapping("setOrderDetails")
	public String setOrderDetails(Model mv, String fname, String lname, String phone, String address,String qty,
											String city,String state, String pincode, String shippingCharge, String orderTotal)
	{
		//System.out.println(fname);
		String loggedInUserid = ((String) session.getAttribute("userId"));// (long)
		user=userDAO.getUserById(loggedInUserid);
		cart=user.getCart();
		List<CartItem> items = cartItemDAO.cartItemGetByCart(cart);
		List<OrderedItems> ordItems;
		
		
		System.out.println(loggedInUserid);
		LocalDateTime datetime1 = LocalDateTime.now();  
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		String orderDate = datetime1.format(format);  
		System.out.println(orderDate+" "+qty+" "+shippingCharge+" "+orderTotal);
		
		OrderDetails orderDetails = new OrderDetails();
		OrderedItems orderedItems = new OrderedItems();
		
		
		orderDetails.setFname(fname);
		orderDetails.setLname(lname);
		orderDetails.setContact(phone);
		orderDetails.setAddress(address);
		orderDetails.setCity(city);
		orderDetails.setState(state);
		orderDetails.setPin(pincode);
		orderDetails.setOrderDate(orderDate);
		orderDetails.setShipping(Integer.parseInt(shippingCharge));
		orderDetails.setOrderTotal(Integer.parseInt(orderTotal));
		orderDetails.setQty(Integer.parseInt(qty));
		orderDetails.setUser(user);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Iterator<CartItem> it = items.iterator();
		while (it.hasNext())
		{
			//System.out.println(iterator.next());
			CartItem c=(CartItem)it.next();
			orderedItems.setProduct(c.getProduct());
			orderedItems.setSell_quantity(c.getSell_quantity());
			orderedItems.setTotal_price(c.getTotal_price());
			orderedItems.setOrderDetails(orderDetails);
			orderedItemsDAO.addOrderItem(orderedItems);
			cartItemDAO.deleteCartItem(c);
		}
		cart.setCartItemCount(0);
		cart.setGrandTotal(0);
		cartDAO.updateCart(cart);
		//orderDAO.createOrder(orderDetails);
			
		
		mv.addAttribute("message", " Order placed successfully");
		
		return "/Home";
	}
	
	@RequestMapping("orderHistory")
	public String orderHistory(Model mv)
	{
		String loggedInUserid = ((String) session.getAttribute("userId"));

		user = userDAO.getUserById(loggedInUserid);
		System.out.println(user.getName());
		OrderDetails o1 = null;
		List<OrderDetails> orders = orderDAO.getOrderDetailsByUser(user);
		List<OrderedItems> itm = orderedItemsDAO.getItemsByOrderID(275);

		Iterator<OrderDetails> it = orders.iterator();
		
		/*while(it.hasNext())
		{
			o1=(OrderDetails)it.next();
			System.out.println(o1.getFname());
		}*/
		mv.addAttribute("isOrderHistory","true");
		mv.addAttribute("orderList", orders);
		mv.addAttribute("OrderedItems",itm);
		
		//System.out.println(o1.getFname());
		return "/Home";
	}
	
	@RequestMapping("viewOrderedItems")
	public String viewOrderedItem(Model mv, @RequestParam("order_id") long orderID)
	{
		System.out.println("OrdreID="+orderID);
		String loggedInUserid = ((String) session.getAttribute("userId"));
		//long orderID=new Long(Order_id).longValue();
		//System.out.println("casted Id="+orderID);
		user = userDAO.getUserById(loggedInUserid);
		
		List<OrderDetails> orders = orderDAO.getOrderDetailsByUser(user);

		List<OrderedItems> itm = orderedItemsDAO.getItemsByOrderID(orderID);
		System.out.println("list size "+itm.size()+" "+orders.size());
		Iterator<OrderedItems> it = itm.iterator();
		OrderedItems o1;
		while(it.hasNext())
		{
			o1=(OrderedItems)it.next();
			System.out.println(o1.getTotal_price());
			System.out.println(o1.getProduct().getName());
			System.out.println(o1.getOrderedItemId());
			mv.addAttribute("orderList",o1);
		}
		System.out.println("list size "+itm.size()+" "+orders.size());

		System.out.println("inside vieworderedItems");
		mv.addAttribute("isOrderedItems", "true");
		mv.addAttribute("OrderedItems",itm);
		
		return "/Home";
	}
}