package com.nagarro.CommunityServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.CommunityServer.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment findByCommentId(long commentId);
	
}
