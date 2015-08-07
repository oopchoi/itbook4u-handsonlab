package demo.ksug.board.module;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.ksug.board.Board;
import demo.ksug.board.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByBoard(Board board);
}
