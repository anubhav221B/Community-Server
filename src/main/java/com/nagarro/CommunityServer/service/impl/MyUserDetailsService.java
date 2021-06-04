package com.nagarro.CommunityServer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.CommunityServer.repository.UserRepository;
import com.nagarro.CommunityServer.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User 404");
		return new UserPrincipal(user);
	}

	public User getUser(String username) {
		User user = repo.findByUsername(username);
		return user;
	}

	public User setUser(User user) {
		if (repo.findByUsername(user.getUsername()) == null)
			return repo.save(user);
		return null;
	}

	public long count() {
		return repo.count();
	}
}
