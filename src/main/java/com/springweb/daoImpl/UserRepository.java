package com.springweb.daoImpl;



import org.springframework.data.jpa.repository.JpaRepository;

import com.springweb.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserId(String userName);
	
}
