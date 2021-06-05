package com.nagarro.CommunityServer.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.CommunityServer.model.Comment;
import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.repository.CommentRepository;
import com.nagarro.CommunityServer.repository.QuestionRepository;
import com.nagarro.CommunityServer.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public Comment setComment(Comment c, long id) {
		Question q = questionRepo.findByQuestionId(id);
		c.setCorrect(false);
		c.setCountLike(0);
		c.setSubject("Re: " + q.getSubject());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		c.setDate(dtf.format(now));
		c.setQuestion(q);
		c = commentRepo.save(c);
		q.setCountComment(q.getComments().size());
		questionRepo.save(q);
		return c;
	}

	@Override
	public Comment getComment(long commentId) {
		return commentRepo.findByCommentId(commentId);
	}

	@Override
	public Comment updateComment(Comment comment) {
		comment = commentRepo.save(comment);
		comment.setCountLike(comment.getLikes().size());
		return commentRepo.save(comment);
	}

	@Override
	public Comment correctComment(Comment comment) {
		Question question = comment.getQuestion();
		question = questionRepo.findByQuestionId(question.getQuestionId());
		question.setAnswered(true);
		questionRepo.save(question);
		comment.setCorrect(true);
		return commentRepo.save(comment);
	}
}
