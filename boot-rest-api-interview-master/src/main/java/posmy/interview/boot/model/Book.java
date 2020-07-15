package posmy.interview.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import posmy.interview.boot.enumeration.BookStatus;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue	
	private Long bookid;
	private String title;
	private String author;
	private Long userid;
	private String status;
	
	public Book() {
		super();
	}

	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		this.status = BookStatus.AVAILABLE.toString();
	}
	
}
