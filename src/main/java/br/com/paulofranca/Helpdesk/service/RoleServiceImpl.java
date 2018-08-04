package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulofranca.Helpdesk.model.Role;
import br.com.paulofranca.Helpdesk.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public List<Role> findAll() {
		List<Role> roles = this.repository.findAll();
		return roles;
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		Role roleCreated = this.repository.save(role);
		return roleCreated;
	}

	@Override
	public Boolean delete(Long id) {
		Role role = this.findById(id);

		if (role != null) {
			this.repository.delete(role);
			return true;
		}

		return false;
	}

	@Override
	public Role findByName(String name) {
		return this.repository.findByName(name);
	}

	private Role findById(Long id) {
		return this.repository.findOne(id);
	}

}
