package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulofranca.Helpdesk.model.User;
import br.com.paulofranca.Helpdesk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
		User userCreated = this.repository.save(user);
		return userCreated;
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
			userExists.setPassword(user.getPassword());
			userExists.setActive(user.getActive());
		}

		return null;
	}

	private User findById(Long id) {
		return this.repository.findOne(id);
	}

}
