package demo.ksug.board.module;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.ksug.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByName(String name);
}
