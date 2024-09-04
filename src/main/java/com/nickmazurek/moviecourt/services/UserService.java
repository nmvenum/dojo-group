package com.codingdojo.moviecourt.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.moviecourt.models.Login;
import com.codingdojo.moviecourt.models.User;
import com.codingdojo.moviecourt.repositories.UserRepo;

// User Service
@Service
public class UserService {
	@Autowired
	private UserRepo uRepo;

	// Register User

	public User registerUser(User u, BindingResult result) {
		// TO-DO - Reject values or register if no errors:

		// Reject if email is taken (present in database)
		Optional<User> exists = uRepo.findByEmail(u.getEmail());
		if (exists.isPresent()) {
			result.rejectValue("email", "Matches", "Email already in use");
		}
		// Reject if password doesn't match confirmation
		if (!u.getConfirm().equals(u.getPassword())) {
			result.rejectValue("confirm", "Matches", "Passwords do not match!");
		}
		// Return null if result has errors
		if (result.hasErrors()) {
			// Exit the method and go back to the controller
			// to handle the response
			return null;
		}

		// Hash and set password, save user to database
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);

		return uRepo.save(u);

	}

	// Login User Method
	 public User login(Login login, BindingResult result) {
	        // Find user in the DB by email
	        Optional<User> exists = uRepo.findByEmail(login.getEmail());
	        
	        // If the user does not exist, reject the login attempt
	        if (exists.isEmpty()) {
	            result.rejectValue("email", "Matches", "Email does not exist");
	            return null; // Return early if user not found
	        }
	        
	        User user = exists.get();

	        // Reject if BCrypt password check fails
	        if (!BCrypt.checkpw(login.getPassword(), user.getPassword())) {
	            result.rejectValue("password", "Matches", "Invalid Password!");
	        }

	        // Return null if there are validation errors
	        if (result.hasErrors()) {
	            return null;
	        }

	        // Otherwise, return the user object
	        return user;
	    }


	// Get all Users

	public List<User> getAllUsers() {
		return uRepo.findAll();
	}

	// Get One User

	public User getUserById(Long id) {
		Optional<User> thisUser = uRepo.findById(id);
		if (thisUser.isEmpty()) {
			return null;
		}

		return thisUser.get();
	}

}
