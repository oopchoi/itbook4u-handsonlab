package demo.ksug.board.module;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ResourceNotFoundException {

	private final long postId;

	public PostNotFoundException(long postId) {
		this.postId = postId;
	}
	
	public long getPostId() {
		return postId;
	}
	
	@Override
	public String getError() {
		return "Post " + HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[]{ postId };
	}

}
