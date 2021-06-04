package com.nagarro.CommunityServer.model;

public class AuthenticationResponse {

	private final String jwt;
	private User user;

	public AuthenticationResponse(String jwt, User userDetails) {
		this.jwt = jwt;
		this.user = userDetails;
	}

	public String getJwt() {
		return jwt;
	}

	public User getUser() {
		return user;
	}

}
