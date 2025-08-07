package com.sinse.mvcapp.exception;

public class CommentException extends RuntimeException{

	public CommentException(String msg) {
		super(msg);
	}
	public CommentException(String msg, Throwable e) {
		super(msg, e);
	}
	public CommentException(Throwable e) {
		super(e);
	}
}
