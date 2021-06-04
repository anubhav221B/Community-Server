package com.nagarro.CommunityServer.repository.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nagarro.CommunityServer.model.Question;
import com.nagarro.CommunityServer.model.SearchParams;
import com.nagarro.CommunityServer.repository.QuestionRepositoryCustom;

public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Question> getBySearch(SearchParams search) {
		String query = "FROM Question WHERE 1=1";
		if (!StringUtils.isBlank(search.getQuestion())) {
			query += " AND subject like '%" + search.getQuestion() + "%'";
		}
		if (!StringUtils.isBlank(search.getProduct())) {
			query += " AND product like '%" + search.getProduct() + "%'";
		}
		if (!StringUtils.isBlank(search.getUsername())) {
			query += " AND user_username like '%" + search.getUsername() + "%'";
		}
		if (!StringUtils.isBlank(search.getTag())) {
			query += " AND tag like '%" + search.getTag() + "%'";
		}
		if (!StringUtils.isBlank(search.getDate())) {
			query += " AND date like '%" + search.getDate() + "%'";
		}
		return em.createQuery(query).getResultList();
	}
}
