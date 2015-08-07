package demo.ksug.board.module;

import org.springframework.http.HttpStatus;

public abstract class ResourceNotFoundException extends RuntimeException {
	
	public HttpStatus getStatus(){
		return HttpStatus.NOT_FOUND;
	}
	
	public String getError(){
		return HttpStatus.NOT_FOUND.getReasonPhrase();
	}
	
	public String getCode(){
		return "error." + getClass().getSimpleName();
	}
	
	public Object[] getArgs(){
		return null;
	}
}
