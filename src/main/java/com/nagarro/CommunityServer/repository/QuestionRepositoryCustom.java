package com.nagarro.CommunityServer.repository;

import java.util.List;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;

public interface QuestionRepositoryCustom {

	List<Question> getBySearch(SearchParams search);

}
