package com.nagarro.CommunityServer.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;
	private String subject;
	private String product;
	@Column(columnDefinition = "TEXT")
	private String body;
	private String date;
	private boolean answered;
	private String tag;
	private int countComment;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@OneToMany(mappedBy = "question")
	private List<Comment> comments = new ArrayList<>();

}
