package com.nagarro.CommunityServer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.CommunityServer.model.AuthenticationRequest;
import com.nagarro.CommunityServer.model.AuthenticationResponse;
import com.nagarro.CommunityServer.model.Status;
import com.nagarro.CommunityServer.model.User;
import com.nagarro.CommunityServer.service.QuestionService;
import com.nagarro.CommunityServer.service.impl.MyUserDetailsService;
import com.nagarro.CommunityServer.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private QuestionService questionService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		User user = userDetailsService.getUser(userDetails.getUsername());

		return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User newUser, HttpServletResponse res) throws IOException {
		User user = userDetailsService.setUser(newUser);
		if (user == null) {
			res.setStatus(500);
			return null;
		}
		return user;
	}

	@GetMapping("/status")
	public Status getStatus() {
		Status status = new Status();
		status.setMembers(userDetailsService.count());
		Status questionStatus = questionService.getQuestionStatus();
		status.setOnline(questionStatus.getOnline());
		status.setPosts(questionStatus.getPosts());
		return status;
	}

}
