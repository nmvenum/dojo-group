package com.codingdojo.moviecourt.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.moviecourt.models.Comment;
import com.codingdojo.moviecourt.models.Login;
import com.codingdojo.moviecourt.models.Movie;
import com.codingdojo.moviecourt.models.User;
import com.codingdojo.moviecourt.services.CommentService;
import com.codingdojo.moviecourt.services.MovieService;
import com.codingdojo.moviecourt.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

// Controller Root Routing

@Controller
public class HomeController {
    @Autowired
    private UserService uServ;
    
    @Autowired
    private MovieService mServ;
    
    @Autowired
    private CommentService cServ;
    
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
            @Valid @ModelAttribute("registerUser") User u,
            BindingResult result,
            Model model,
            HttpSession session)  {
        
        User currentUser = uServ.registerUser(u, result);
        
        if (result.hasErrors()) {
            model.addAttribute("loginUser", new Login());
            return "index.jsp";
        }
        
        Long userId = currentUser.getId();
        session.setAttribute("userId", userId);
        
        return "redirect:/dashboard";
    }
        
    // Login Method
    @PostMapping("/login")
    public String loginRoute(
            @Valid @ModelAttribute("loginUser") Login l,
            BindingResult result,
            Model model,
            HttpSession session)  {
        
        User currentUser = uServ.login(l, result);
        
        if (result.hasErrors()) {
            model.addAttribute("registerUser", new User());
            return "index.jsp";
        }
        
        Long userId = currentUser.getId();
        session.setAttribute("userId", userId);
        
        return "redirect:/dashboard";
    }
        
    // Welcome Page - All Movies
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/loginRedirect";
        }
        
        User user = uServ.getUserById(userId); // Adjust to your actual method
        List<Movie> allMovies = mServ.getAllMovie(); // Ensure this is not empty
        
        model.addAttribute("user", user);
        model.addAttribute("movies", allMovies);
        
        return "home.jsp";
    }

    
    // New Movie Form
    @GetMapping("/watchMovie")
    public String watchMovie(
            Model model,
            HttpSession session) {
        
        if (session.getAttribute("userId") == null) {
            return "redirect:/loginRedirect";
        }
        
        model.addAttribute("newMovie", new Movie());
        return "create.jsp";
    }
    
    @PostMapping("/processWatchMovie")
    public String processWatchMovie(
            Model model,
            @Valid @ModelAttribute("newMovie") Movie newMovie,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        if (session.getAttribute("userId") == null) {
            return "redirect:/loginRedirect";
        }
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Please include a valid movie title, a rating between 1-5, and a comment about the movie.");
        	model.addAttribute("newMovie", newMovie);
            return "create.jsp";
        }
        
        Movie processedMovie = mServ.createMovie(newMovie, (Long) session.getAttribute("userId"));
        return "redirect:/moviePage/" + processedMovie.getId();
    }
    
    @GetMapping("/moviePage/{movieId}")
    public String moviePage(
            Model model,
            @PathVariable("movieId") Long movieId,
            HttpSession session) {

        Movie movie = mServ.getOneMovie(movieId);
        User user = uServ.getUserById((Long) session.getAttribute("userId")); // Get current user from session

        model.addAttribute("movie", movie);
        model.addAttribute("user", user); // Add user to model
        model.addAttribute("commentForm", new Comment()); // Add an empty Comment object for the form

        return "details.jsp";
    }

    
    // Update Movie Review Form
    @GetMapping("/updateReviewPage/{movieId}")
    public String updateReviewPage(
            Model model,
            @PathVariable("movieId") Long movieId,
            HttpSession session) {
        
        Movie movie = mServ.getOneMovie(movieId);
        Long sessionUserId = (Long) session.getAttribute("userId");
        Long movieUserId = movie.getUser().getId();

        if (!movieUserId.equals(sessionUserId)) {
            return "redirect:/unauthorizedUser";
        }
        
        model.addAttribute("movie", movie); // Ensure the movie is correctly set up
        return "update.jsp";
    }


    @PostMapping("/processUpdateReview/{movieId}")
    public String processUpdateReview(
            Model model,
            @Valid @ModelAttribute("movie") Movie processedMovie,
            BindingResult result,
            @PathVariable("movieId") Long movieId,
            HttpSession session) {

        if (result.hasErrors()) {
            processedMovie.setId(movieId); // Ensure the ID is maintained
            model.addAttribute("movie", processedMovie);
            return "update.jsp"; // Return to the update form if errors
        }
        
        mServ.updateMovie(processedMovie, movieId, (Long) session.getAttribute("userId"));
        return "redirect:/moviePage/" + movieId;
    }

    
    @DeleteMapping("/deleteMovie/{movieId}")
    public String deleteMovie(
            @PathVariable("movieId") Long movieId,
            HttpSession session) {
        
        mServ.delete(movieId);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/loginRedirect")
    public String loginRedirect(
            Model model,
            @ModelAttribute("loginUser") Login l,
            @ModelAttribute("registerUser") User u) {
        
        model.addAttribute("loginNeeded", "Must be logged in to view!!");
        return "index.jsp";
    }
    
    @PostMapping("/addComment")
    public String addComment(
            @Valid @ModelAttribute("commentForm") Comment comment,
            BindingResult result,
            @RequestParam("movieId") Long movieId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid comment.");
            return "redirect:/moviePage/" + movieId;
        }

        Long userId = (Long) session.getAttribute("userId");
        comment.setUser(uServ.getUserById(userId));
        comment.setMovie(mServ.getOneMovie(movieId));
        cServ.createComment(comment);

        return "redirect:/moviePage/" + movieId;
    }
    
    @DeleteMapping("/deleteComment/{commentId}")
    public String deleteComment(
            @PathVariable("commentId") Long commentId,
            HttpSession session) {

        Comment comment = cServ.getCommentById(commentId);
        Long sessionUserId = (Long) session.getAttribute("userId");
        Long commentUserId = comment.getUser().getId();

        if (!commentUserId.equals(sessionUserId)) {
            return "redirect:/unauthorizedUser";
        }

        cServ.deleteComment(commentId);
        return "redirect:/moviePage/" + comment.getMovie().getId();
    }
    @GetMapping("/editComment/{commentId}")
    public String editComment(
            @PathVariable("commentId") Long commentId,
            Model model,
            HttpSession session) {

        Comment comment = cServ.getCommentById(commentId);
        Long sessionUserId = (Long) session.getAttribute("userId");
        Long commentUserId = comment.getUser().getId();

        if (!commentUserId.equals(sessionUserId)) {
            return "redirect:/unauthorizedUser";
        }

        model.addAttribute("commentForm", comment); // Ensure this is added
        return "editComment.jsp";
    }

    @PostMapping("/updateComment/{commentId}")
    public String updateComment(
            @Valid @ModelAttribute("commentForm") Comment updatedComment,
            BindingResult result,
            @PathVariable("commentId") Long commentId,
            @RequestParam("movieId") Long movieId,
            HttpSession session) {

        if (result.hasErrors()) {
            return "editComment.jsp";
        }

        Comment comment = cServ.getCommentById(commentId);
        Long sessionUserId = (Long) session.getAttribute("userId");
        Long commentUserId = comment.getUser().getId();

        if (!commentUserId.equals(sessionUserId)) {
            return "redirect:/unauthorizedUser";
        }

        comment.setText(updatedComment.getText());
        cServ.createComment(comment);

        return "redirect:/moviePage/" + movieId;
    }
    
    @GetMapping("/logout")
    public String logout(
            HttpSession session) {
        
        session.invalidate();
        return "redirect:/";
    }
}
