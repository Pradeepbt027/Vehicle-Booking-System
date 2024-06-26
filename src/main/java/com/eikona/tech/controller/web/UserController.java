package com.eikona.tech.controller.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eikona.tech.dto.PaginationDto;
import com.eikona.tech.entity.User;
import com.eikona.tech.service.RoleService;
import com.eikona.tech.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('user_view')")
	public String list() {
		return "user/user_list";
	}

	@GetMapping("/user/new")
	@PreAuthorize("hasAuthority('user_create')")
	public String newUser(Model model, Principal principal) {
		
		model.addAttribute("listRole", roleService.getAll());
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("title", "New User");
		return "user/user_new";
	}

	@PostMapping("/user/add")
	@PreAuthorize("hasAnyAuthority('user_create','user_update')")
	public String saveUser(@ModelAttribute("user") User user, @Valid User users, Errors errors, BindingResult bindingResult,
			Model model, String title, Principal principal) {
		if (errors.hasErrors()) {
			
			model.addAttribute("title", title);
			model.addAttribute("listRole", roleService.getAll());
			return "user/user_new";
		}
		model.addAttribute("message", "Add Successfully");
		userService.save(user);
		return "redirect:/user";

	}

	@GetMapping("/user/edit/{id}")
	@PreAuthorize("hasAuthority('user_update')")
	public String editUser(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("listRole", roleService.getAll());
		User user = userService.getById(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "Update User");
		return "user/user_new";
	}

	@GetMapping("user/delete/{id}")
	@PreAuthorize("hasAuthority('user_delete')")
	public String deleteUser(@PathVariable(value = "id") Integer id) {

		this.userService.deleteById(id);
		return "redirect:/user";
	}

	@RequestMapping(value = "/api/search/user", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('user_view')")
	public @ResponseBody PaginationDto<User> searchDoor(Long id, String name,String phone,String role, int pageno, String sortField, String sortDir, Principal principal) {
		
		PaginationDto<User> dtoList = userService.searchByField(id, name, phone,role,  pageno, sortField, sortDir);
		return dtoList;
	}
}
