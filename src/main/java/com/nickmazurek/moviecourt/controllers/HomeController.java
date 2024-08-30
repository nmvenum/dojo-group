package com.nickmazurek.moviecourt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nickmazurek.moviecourt.models.Login;
import com.nickmazurek.moviecourt.models.User;
import com.nickmazurek.moviecourt.services.MovieService;
import com.nickmazurek.moviecourt.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

// Controller Root Routing

@Controller
public class HomeController {
	@Autowired
	private UserService uServ;
	
	@Autowired
	private MovieService mServ;
	
	@GetMapping("/")
	public String index(
		@ModelAttribute("loginUser") Login l,
		@ModelAttribute("registerUser") User u
		) {
		
		return "index.jsp"; 
	}
	
	// Register Method
	@PostMapping("/register")
	public String registerRoute(
			@Valid@ModelAttribute("registerUser") User u,
			BindingResult result,
			Model model,
			HttpSession session
			) 	{
		
			User currentUser = uServ.registerUser(u,result);
		
			if(result.hasErrors()) {
				model.addAttribute("loginUser",new Login());
				return "index.jsp";
		}
		
		Long userId = currentUser.getId();
		session.setAttribute("userId", userId);
		
		
		
		
		return "redirect:/dashboard";
	}
		
		// Login Method
		@PostMapping("/login")
		public String loginRoute(
				@Valid@ModelAttribute("loginUser") Login l,
				BindingResult result,
				Model model,
				HttpSession session
				) 	{
				User currentUser = uServ.login(l, result);
				if(result.hasErrors()) {
					model.addAttribute("registerUser", new User());
					return "index.jsp";
			}
				
			Long userId = currentUser.getId();
			session.setAttribute("userId", userId);
				
			
			
			
			return "redirect:/dashboard";
	}
}
