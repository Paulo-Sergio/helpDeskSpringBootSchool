package br.com.paulofranca.Helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulofranca.Helpdesk.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
