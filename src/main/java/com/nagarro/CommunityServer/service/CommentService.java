package com.nagarro.CommunityServer.service;

import com.nagarro.CommunityServer.model.Comment;

public interface CommentService {

	Comment setComment(Comment c, long id);

	Comment getComment(long commentId);

	Comment updateComment(Comment c);

	Comment correctComment(Comment c);

}
