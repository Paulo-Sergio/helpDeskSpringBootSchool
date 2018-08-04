package br.com.paulofranca.Helpdesk.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.paulofranca.Helpdesk.model.Role;
import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.repository.RoleRepository;
import br.com.paulofranca.Helpdesk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
		// encriptando senha na criação do usuario
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		// buscando a role padãro USER
		Role userRole = this.roleRepository.findByName("USER");

		// setando roles do usuário (criação de user só vai "USER" por padrão)
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		return this.repository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		User user = this.findById(id);

		if (user != null) {
			this.repository.delete(user);
			return true;
		}

		return false;
	}

	@Override
	public User show(Long id) {
		return this.findById(id);
	}

	@Override
	public Boolean update(Long id, User user) {
		User userExists = this.findById(id);

		if (userExists != null) {
			userExists.setId(user.getId());
			userExists.setName(user.getName());
			userExists.setLastName(user.getLastName());
			userExists.setEmail(user.getEmail());
			userExists.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userExists.setActive(user.getActive());

			// atualizando a Role do usuário
			Role userRole = this.roleRepository.findByName(user.getRoles().iterator().next().getName());
			userExists.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

			this.repository.save(userExists);

			return true;
		}

		return false;
	}

	@Override
	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
		return this.repository.findAllWhereRoleEquals(role_id, user_id);
	}

	private User findById(Long id) {
		return this.repository.findOne(id);
	}

}
