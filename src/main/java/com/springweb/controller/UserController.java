package com.springweb.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.exception.UserNotFoundException;
import com.springweb.model.User;
import com.springweb.svc.UserSvc;

@Controller
public class UserController {

	@Autowired
	UserSvc userSvc;

	@RequestMapping(value = "/registerUser")
	public String showRegistrationPage(@ModelAttribute("registrationForm") User newUser, Model model) {
		User user = new User();
		Map<String, String> roles = userSvc.getRoleNames();
		model.addAttribute("rolesList", roles);
		model.addAttribute("registrationForm", user);
		return "registration";
	}

	@PostMapping(value = "/saveUserRegistration")
	public ModelAndView saveUserRegistration(@Valid @ModelAttribute("registrationForm") User newUser,
			BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registration");
		if (result.hasErrors()) {
			return mv;
		}
		newUser = userSvc.getUserRoleList(newUser);
		userSvc.addUser(newUser);
		mv.addObject("registrationForm", new User());
		Map<String, String> roles = userSvc.getRoleNames();
		mv.addObject("rolesList", roles);
		mv.addObject("saveStatus", "User saved successfully");
		return mv;
	}

	@GetMapping(value = "/addUser")
	public String addNewUser(@ModelAttribute("userUpdateForm") User newUser, Model model) {
		Map<String, String> roles = userSvc.getRoleNames();
		model.addAttribute("userUpdateForm", new User());
		model.addAttribute("addOrUpdate", "add");
		model.addAttribute("rolesList", roles);
		return "add_edit_user";
	}

	@PostMapping(value = "/saveUser")
	public ModelAndView saveUpdateUser(@Valid @ModelAttribute("userUpdateForm") User user, BindingResult result,
			Model model, @RequestParam String opType) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			return mv;
		}
		user = userSvc.getUserRoleList(user);
		if ("add".equals(opType)) {
			userSvc.addUser(user);
			mv.addObject("userUpdateForm", new User());
			mv.addObject("saveStatus", "User created successfully");
			List<User> users = userSvc.getAllUsers();
			mv.addObject("usersList", users);
			mv.setViewName("users");
		} else if ("update".equals(opType)) {
			userSvc.updateUser(user);
			//mv.addObject("userUpdateForm", userSvc.getUserRoleList(user));
			model.addAttribute("rolesList", userSvc.getRoleNames());
			mv.addObject("saveStatus", "User updated successfully");
			mv.setViewName("add_edit_user");
		}
		return mv;
	}

	@GetMapping("/showUsers")
	public ModelAndView getAllUsers(@RequestParam(required = false, name="isAccessError") String isForbidden) {
		ModelAndView mv = new ModelAndView();
		List<User> users = userSvc.getAllUsers();
		mv.addObject("usersList", users);
		mv.setViewName("users");
		if("true".equals(isForbidden)) {
			mv.addObject("saveStatus", "User does not have permission to edit");
		}
		return mv;
	}

	@GetMapping("/getUser/{userId}")
	public String getUser(@PathVariable String userId, Model model) {
		User user = userSvc.getUser(userId);
		model.addAttribute("userUpdateForm", user);
		return "add_edit_user";
	}

	@PostMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable String userId) {
		userSvc.deleteUser(userId);
		// return "redirect:/showUsers";
		return "users";
	}

	@GetMapping("/editUser/{userId}")
	public ModelAndView editUser(@ModelAttribute("userUpdateForm") User updateUser, @PathVariable String userId) {
		ModelAndView mv = new ModelAndView();
		updateUser = userSvc.getUser(userId);
		// updateUser = null;
		if (updateUser == null) {
			throw new UserNotFoundException();
		}
		updateUser = userSvc.getUserRoleList(updateUser);
		String[] selectedRoles = userSvc.getSelectedRolesForUser(updateUser);
		updateUser.setSelectedRoles(selectedRoles);
		mv.addObject("userUpdateForm", updateUser);
		mv.addObject("rolesList", userSvc.getRoleNames());
		mv.setViewName("add_edit_user");
		return mv;
		// return "redirect:/showUsers";
	}

}
