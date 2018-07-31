package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import br.com.paulofranca.Helpdesk.model.Role;

public interface RoleService {
	
	public List<Role> findAll();
	
	public Role create(Role role);

	public Boolean delete(Long id);
	
}
