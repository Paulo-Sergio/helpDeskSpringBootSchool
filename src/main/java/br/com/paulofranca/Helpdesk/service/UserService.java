package br.com.paulofranca.Helpdesk.service;

import java.util.List;

import br.com.paulofranca.Helpdesk.model.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User create(User user);
	
	public Boolean delete(Long id);
	
	public Boolean update(Long id, User user);

	public User show(Long id);
	
	public List<User> findAllWhereRoleEquals(Long role_id);
}
