package com.nickmazurek.moviecourt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nickmazurek.moviecourt.models.Login;
import com.nickmazurek.moviecourt.models.Movie;
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
		
		// Welcome Page - All Movies
		@GetMapping("/dashboard")
		public String dashboard(
				Model model) {
			List<Movie> allMovies = mServ.getAllMovie();
			model.addAttribute("allMovies", allMovies);
			return "dashboard.jsp";
		}
		
		// New Movie Form
		@GetMapping("/watchMovie")
		public String watchMovie(
				Model model,
				HttpSession session) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/loginRedirect";
			}
			model.addAttribute("newMovie", new Movie());
			return "watchMovie.jsp";
		}
		
		@PostMapping("/processWatchMovie")
		public String processWatchMovie(
				Model model,
				@Valid
				@ModelAttribute("newMovie") Movie newMovie,
				BindingResult result,
				HttpSession session) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/loginRedirect";
			}
			if(result.hasErrors()) {
				model.addAttribute("newMovie", newMovie);
				return "watchMovie.jsp";
			}
			Movie processedMovie = mServ.createMovie(newMovie, (Long)session.getAttribute("userId"));
			return "redirect:/moviePage/" + processedMovie.getId();
		}
		
		// Movie Page
		@GetMapping("/moviePage/{movieId}")
		public String moviePage(
				Model model,
				@PathVariable("movieId") Long movieId) {
			Movie movie = mServ.getOneMovie(movieId);
			model.addAttribute("movie", movie);
			return "moviePage.jsp";
		}
		
		// Update Movie Review Form
		@GetMapping("/updateReviewPage/{movieId}")
		public String updateReviewPage(
				Model model,
				@PathVariable("movieId") Long movieId,
				HttpSession session) {
			Movie movie = mServ.getOneMovie(movieId);
			if(movie.getUser().getId() != (Long)session.getAttribute("userId")) {
				return "redirect:/unauthorizedUser";
			}
			model.addAttribute("movie", movie);
			return "updateReview.jsp";
		}
		
		@PostMapping("/processUpdateReview/{movieId}")
		public String processUpdateReview(
				Model model,
				@Valid
				@ModelAttribute("movie") Movie processedMovie,
				BindingResult result,
				@PathVariable("movieId") Long movieId,
				HttpSession session) {
			if(result.hasErrors()) {
				processedMovie.setId(movieId);
				model.addAttribute("movie", processedMovie);
				return "updateReview.jsp";
			}
			Movie movie = mServ.getOneMovie(movieId);
			if(movie.getUser().getId() != (Long)session.getAttribute("userId")) {
				return "redirect:/unauthorizedUser";
			}
			mServ.updateMovie(processedMovie, movieId, (Long)session.getAttribute("userId"));
			return "redirect:/moviePage/" + movieId;
		}
		
		@DeleteMapping("/deleteMovie/{movieId}")
		public String deleteMovie(
				@PathVariable("movieId") Long movieId,
				HttpSession session) {
			Movie movie = mServ.getOneMovie(movieId);
			if(movie.getUser().getId() != (Long)session.getAttribute("userId")) {
				return "redirect:/unauthorizedUser";
			}
			mServ.delete(movieId);
			return "redirect:/dashboard";
		}
		
		@GetMapping("/unauthorizedUser")
		public String unauthorizedUser(
				Model model) {
			model.addAttribute("unauthorized", "Users can only edit reviews they wrote!!");
			List<Movie> allMovies = mServ.getAllMovie();
			model.addAttribute("allMovies", allMovies);
			return "dashboard.jsp";
		}
		
		@GetMapping("/loginRedirect")
		public String loginRedirect(
				Model model,
				@ModelAttribute("loginUser") Login l,
				@ModelAttribute("registerUser") User u) {
			model.addAttribute("loginNeeded", "Must be logged in to view!!");
			return "index.jsp";
		}
		
		@PostMapping("/logout")
		public String logout(
				HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
		
		
}