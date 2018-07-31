package br.com.paulofranca.Helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

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
		model.addAttribute("user", user);
		
		return "users/edit";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.userService.delete(id);
		return "redirect:/users";
	}
}
