package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String listAllUsers(Model model) {
		List<User> listOfUsers = userService.listAllUsers();
		model.addAttribute("listOfUsers", listOfUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = userService.listRoles();
		
		User user = new User();
		model.addAttribute("user", user);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		System.out.println(user);
		return "redirect:/users";
	}

}
