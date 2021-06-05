package com.nagarro.CommunityServer.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;
import com.nagarro.CommunityServer.model.Status;
import com.nagarro.CommunityServer.model.User;
import com.nagarro.CommunityServer.repository.QuestionRepository;
import com.nagarro.CommunityServer.service.QuestionService;

@Service
public class QusetionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionsRepo;

	@Override
	public Question setQuestion(Question question) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		question.setAnswered(false);
		question.setCountComment(0);
		question.setDate(dtf.format(now));
		return questionsRepo.save(question);
	}

	@Override
	public Question getQuestionById(long id) {
		return questionsRepo.findByQuestionId(id);
	}

	@Override
	public List<Question> getQuestions(SearchParams search) {
		return questionsRepo.getBySearch(search);
	}

	@Override
	public List<Question> getQuestionsByUser(User user) {
		return questionsRepo.findAllByUser(user);
	}

	@Override
	public Status getQuestionStatus() {
		Status status = new Status();
		status.setPosts(questionsRepo.countOfQuestion());
		status.setOnline(questionsRepo.countOfLiveQuestion());
		return status;
	}

}
