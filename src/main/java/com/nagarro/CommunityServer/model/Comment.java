package com.nagarro.CommunityServer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentId;
	private String subject;
	@Column(columnDefinition = "TEXT")
	private String body;
	private String date;
	private boolean correct;
	private int countLike;
	@Transient
	private boolean liked;
	@ManyToOne
	private User user;
	@ManyToMany
	@JsonIgnore
	private List<User> likes = new ArrayList<>();
	@ManyToOne
	@JsonIgnore
	private Question question;

}
