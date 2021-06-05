package com.nagarro.CommunityServer.service;

import com.nagarro.CommunityServer.model.Comment;

public interface CommentService {
	/**
	 * this method will add comment to database using question id
	 * 
	 * @param comment
	 * @param id is the Question id 
	 * @return comment
	 */
	Comment setComment(Comment comment, long id);
	
	/**
	 * this method get the comment using comment id
	 * 
	 * @param commentId
	 * @return comment
	 */
	Comment getComment(long commentId);

	/**
	 * this will mark comment as liked
 	 * 
	 * @param comment
	 * @return updated comment
	 */
	Comment updateComment(Comment comment);

	/**
	 * this will mark comment as correct comment of question
	 * 
	 * @param comment
	 * @return correct comment
	 */
	Comment correctComment(Comment comment);

}
