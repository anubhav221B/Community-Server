package com.nagarro.CommunityServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.User;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
	
	List<Question> findAllByUser(User u);

	Question findByQuestionId(Long questionId);

	@Query(value = "SELECT COUNT(*) FROM Question")
	long countOfQuestion();

	@Query(value = "SELECT COUNT(*) FROM Question where answered = false")
	long countOfLiveQuestion();
	
}
