package demo.ksug.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String author;
	private String title;
	private String content;
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Board board;
	
	protected Post() { }
	
	protected Post(Board board, String author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
		this.createdAt = new Date();
		
		this.board = board;
	}
	
	public Post update(String author, String title, String content) {
		System.out.println("update():" + author + ", " + title + ", " + content);
		this.author = author;
		this.title = title;
		this.content = content;
		
		return this;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	protected Board getBoard() {
		return board;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Post))
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
