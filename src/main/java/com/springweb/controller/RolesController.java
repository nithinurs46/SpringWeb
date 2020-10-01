package com.springweb.controller;

import java.util.List;

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

import com.springweb.exception.RoleNotFoundException;
import com.springweb.model.Role;
import com.springweb.svc.UserSvc;

@Controller
public class RolesController {

	@Autowired
	UserSvc userSvc;

	@GetMapping("/showRoles")
	public ModelAndView getAllRoles() {
		ModelAndView mv = new ModelAndView();
		List<Role> roles = userSvc.getAllRoles();
		mv.addObject("rolesList", roles);
		mv.setViewName("roles");
		return mv;
	}

	@RequestMapping(value = "/addRole")
	public String showRolePage(@ModelAttribute("roleUpdateForm") Role role, Model model) {
		model.addAttribute("roleUpdateForm", new Role());
		model.addAttribute("addOrUpdate", "add");
		return "add_edit_role";
	}

	@PostMapping(value = "/saveRole")
	public ModelAndView saveUpdateRole(@Valid @ModelAttribute("roleUpdateForm") Role role, BindingResult result,
			Model model, @RequestParam String opType) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			return mv;
		}

		if ("add".equals(opType)) {
			userSvc.addRole(role);
			mv.addObject("roleUpdateForm", new Role());
			mv.addObject("saveStatus", "Role created successfully");
			List<Role> roles = userSvc.getAllRoles();
			mv.addObject("rolesList", roles);
			mv.setViewName("roles");
		} else if ("update".equals(opType)) {
			userSvc.updateRole(role);
			mv.addObject("roleUpdateForm", role);
			mv.addObject("saveStatus", "Role updated successfully");
			mv.setViewName("add_edit_role");
		}

		return mv;
	}

	@GetMapping("/editRole/{roleName}")
	public String editUser(@ModelAttribute("roleUpdateForm") Role role, @PathVariable String roleName, Model model) {
		role = userSvc.getRole(roleName);
		//role = null;
		if(role == null) {
			throw new RoleNotFoundException();
		}
		model.addAttribute("roleUpdateForm", role);
		model.addAttribute("addOrUpdate", "update");
		return "add_edit_role";
	}

	@PostMapping("/deleteRole/{roleName}")
	public String deleteRole(@PathVariable String roleName, Model model) {
		userSvc.deleteRole(roleName);
		model.addAttribute("saveStatus", "Role deleted successfully");
		return "roles";
	}
}
