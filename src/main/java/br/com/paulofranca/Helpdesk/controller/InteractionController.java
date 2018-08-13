package br.com.paulofranca.Helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulofranca.Helpdesk.model.Interaction;
import br.com.paulofranca.Helpdesk.service.InteractionService;

@Controller
@RequestMapping("/tickets/{tickedId}/interactions")
public class InteractionController {

	@Autowired
	private InteractionService interactionService;

	@PostMapping
	public String save(@PathVariable("tickedId") Long tickedId, @Valid @ModelAttribute("interaction") Interaction iteraction,
			BindingResult bindingResult, Model model) {

		return null;
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("tickedId") Long tickedId, @PathVariable("id") Long id, Model model) {

		return null;
	}
}
