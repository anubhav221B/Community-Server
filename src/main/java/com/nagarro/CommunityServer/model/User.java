package com.nagarro.CommunityServer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User {
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(56)")
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String fName;
	private String lName;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Question> questions = new ArrayList<>();
	@ManyToMany(mappedBy = "likes")
	@JsonIgnore
	private List<Comment> likeComment = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Comment> getLikeComment() {
		return likeComment;
	}

	public void setLikeComment(List<Comment> likeComment) {
		this.likeComment = likeComment;
	}

	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		return this.username.equals(u.getUsername());
	}

}
