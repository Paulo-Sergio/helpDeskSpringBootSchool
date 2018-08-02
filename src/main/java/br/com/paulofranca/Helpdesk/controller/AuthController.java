package br.com.paulofranca.Helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.paulofranca.Helpdesk.model.User;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}
}
