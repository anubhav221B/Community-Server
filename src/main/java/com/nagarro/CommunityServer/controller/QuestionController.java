package com.nagarro.CommunityServer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.CommunityServer.model.Comment;
import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;
import com.nagarro.CommunityServer.model.User;
import com.nagarro.CommunityServer.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping("/questions")
	public Question postQuestion(@RequestBody Question addQuestion, HttpServletRequest req) {
		HttpSession session = req.getSession();
		addQuestion.setUser((User) session.getAttribute("user"));
		return questionService.setQuestion(addQuestion);
	}

	@PostMapping("/questions/search")
	public List<Question> getQuestionBySearch(@RequestBody SearchParams search) {
		List<Question> questions = questionService.getQuestions(search);
		for (Question question : questions) {
			question.setComments(null);
		}
		return questions;
	}

	@GetMapping("/questions")
	public List<Question> getQuestions(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Question> questions = questionService.getQuestionsByUser(user);
		for (Question question : questions) {
			question.setComments(null);
		}
		return questions;
	}

	@GetMapping("/questions/{id}")
	public Question getQuestion(@PathVariable("id") long id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Question question = questionService.getQuestionById(id);
		List<Comment> comments = question.getComments();
		for (Comment comment : comments) {
			comment.setLiked(comment.getLikes().contains(user));
		}
		return question;
	}
}
