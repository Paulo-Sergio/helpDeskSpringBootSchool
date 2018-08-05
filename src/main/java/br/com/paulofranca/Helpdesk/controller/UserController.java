package br.com.paulofranca.Helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Role;
import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.service.RoleService;
import br.com.paulofranca.Helpdesk.service.UserService;

/**
 *	@PreAuthorize: vai rodar uma filtro antes de entrar dentro de um controller
 *	solução do SpringSecurity
 */

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.userService.findAll());
		return "users/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "users/create";
		}

		this.userService.create(user);

		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		User user = this.userService.show(id);
		List<Role> roles = this.roleService.findAll();
		
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);

		return "users/edit";
	}

	@PutMapping("/edit/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "users/edit";
		}

		this.userService.update(id, user);

		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.userService.delete(id);
		return "redirect:/users";
	}
}
