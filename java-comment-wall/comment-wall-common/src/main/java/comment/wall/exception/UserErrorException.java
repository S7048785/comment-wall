package comment.wall.exception;

public class UserErrorException extends BaseException{
	public UserErrorException() {
		super();
	}
	
	public UserErrorException(String message) {
		super(message);
	}
}
