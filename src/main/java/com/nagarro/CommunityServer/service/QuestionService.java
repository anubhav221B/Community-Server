package com.nagarro.CommunityServer.service;

import java.util.List;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;
import com.nagarro.CommunityServer.model.Status;
import com.nagarro.CommunityServer.model.User;

public interface QuestionService {

	/**
	 * this method will add new question into database
	 * 
	 * @param question
	 * @return question
	 */
	Question setQuestion(Question question);

	/**
	 * this method will get all the questions added by current user
	 * 
	 * @param user
	 * @return list
	 */
	List<Question> getQuestionsByUser(User user);

	/**
	 * this method will get the status of the questions
	 * 
	 * @return status
	 */
	Status getQuestionStatus();

	/**
	 * this method will get the questions with respect to search parameter
	 * 
	 * @param search
	 * @return
	 */
	List<Question> getQuestions(SearchParams search);

	/**
	 * this method will get question by question id
	 * 
	 * @param id is the question id
	 * @return
	 */
	Question getQuestionById(long id);
	
}
