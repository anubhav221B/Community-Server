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
	public Question setQuestion(Question q) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		q.setAnswered(false);
		q.setCountComment(0);
		q.setDate(dtf.format(now));
		return questionsRepo.save(q);
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
	public List<Question> getQuestionsByUser(User u) {
		return questionsRepo.findAllByUser(u);
	}

	@Override
	public Status getQuestionStatus() {
		Status s = new Status();
		s.setPosts(questionsRepo.countOfQuestion());
		s.setOnline(questionsRepo.countOfLiveQuestion());
		return s;
	}

}
