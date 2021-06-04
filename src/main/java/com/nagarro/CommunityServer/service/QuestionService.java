package com.nagarro.CommunityServer.service;

import java.util.List;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;
import com.nagarro.CommunityServer.model.Status;
import com.nagarro.CommunityServer.model.User;

public interface QuestionService {

	Question setQuestion(Question q);

	List<Question> getQuestionsByUser(User u);

	Status getQuestionStatus();

	List<Question> getQuestions(SearchParams search);

	Question getQuestionById(long id);
	
}
