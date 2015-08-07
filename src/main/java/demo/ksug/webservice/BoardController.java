package demo.ksug.webservice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.ksug.board.Board;
import demo.ksug.board.Post;
import demo.ksug.board.module.BoardService;
import demo.ksug.board.module.PostForm;
import demo.ksug.board.module.ResourceNotFoundException;
import demo.ksug.webservice.support.MessageSourceAccessor;

@RestController
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private MessageSourceAccessor messageSource;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	@Autowired
	public void setMessageSource(MessageSource messageSource){
		this.messageSource = new MessageSourceAccessor(messageSource);
	}
	
	@RequestMapping(value = "/{boardname}/info", method = { RequestMethod.GET, RequestMethod.HEAD })
	public ResponseEntity<Board> info(@PathVariable String boardname) {
		return ResponseEntity.ok(boardService.findBoard(boardname));
	}
	
	@RequestMapping(value = "/{boardname}", method = { RequestMethod.GET, RequestMethod.HEAD })
	public ResponseEntity<Board> free(@PathVariable String boardname) {
		System.out.println(boardname);
		return ResponseEntity.ok(boardService.findBoard(boardname));
	}
	
	@RequestMapping(value = "/{boardname}/list", method = { RequestMethod.GET, RequestMethod.HEAD })
	public ResponseEntity<List<Post>> listPosts(@PathVariable String boardname) {
		return ResponseEntity.ok(boardService.findPosts(boardname));
	}
	
	@RequestMapping(value = "/{boardname}", method = { RequestMethod.POST })
	public ResponseEntity<Post> createPost(@PathVariable String boardname, @Valid PostForm postForm) {
		return ResponseEntity.status(HttpStatus.CREATED).body(boardService.writePost(boardname, postForm));
	}
	
	@RequestMapping(value = "/{boardname}/{postId}", method = { RequestMethod.PUT })
	public ResponseEntity<Post> updatePost(@PathVariable Long postId, String author, String title, String content) {
		System.out.println("updatePost():" + postId + ", " + author + ", " + title + ", " + content);
		return ResponseEntity.ok(boardService.editPost(postId, author, title, content));
	}
	
	@RequestMapping(value = "/{boardname}/{postId}", method = { RequestMethod.DELETE })
	public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
		return ResponseEntity.ok(boardService.erasePost(postId));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> resourceNotFoundException(ResourceNotFoundException exception, Locale locale){
		System.out.print(exception.getError());
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", exception.getStatus());
		body.put("error", exception.getError());
		body.put("message", messageSource.getMessage(exception.getCode(), exception.getArgs(), locale).orElse("No message available"));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

}
