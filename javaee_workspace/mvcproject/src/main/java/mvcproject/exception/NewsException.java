package mvcproject.exception;

public class NewsException extends RuntimeException{

	public NewsException(String msg) {
		super(msg);
	}
	public NewsException(String msg, Throwable e) {
		super(msg, e);
	}
	public NewsException(Throwable e) {
		super(e);
	}
}
