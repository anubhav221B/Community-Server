package com.nagarro.CommunityServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.CommunityServer.model.Comment;
import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.User;
import com.nagarro.CommunityServer.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/comment/{id}")
	public Comment setCommentByQuestion(@PathVariable("id") long id, @RequestBody Comment comment,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		comment.setUser((User) session.getAttribute("user"));
		return commentService.setComment(comment, id);
	}

	@PutMapping("/comment/like/{id}")
	public Comment likeCommentByComment(@PathVariable("id") long id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Comment comment = commentService.getComment(id);
		if (comment.getLikes().contains(user)) {
			return null;
		}
		comment.getLikes().add(user);
		comment = commentService.updateComment(comment);
		comment.setLiked(true);
		return comment;
	}

	@PutMapping("/comment/correct/{id}")
	public Comment correctCommentByComment(@PathVariable("id") long id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Comment comment = commentService.getComment(id);
		Question question = comment.getQuestion();
		if (!question.getUser().getUsername().equals(user.getUsername()))
			return null;
		return commentService.correctComment(comment);
	}
}
