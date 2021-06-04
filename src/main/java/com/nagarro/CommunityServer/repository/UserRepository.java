package com.nagarro.CommunityServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.CommunityServer.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsername(String username);

	@Query(value = "SELECT COUNT(*) FROM User")
	long countOfUser();
	
}
