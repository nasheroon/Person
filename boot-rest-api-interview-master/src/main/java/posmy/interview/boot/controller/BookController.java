package posmy.interview.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import posmy.interview.boot.enumeration.BookStatus;
import posmy.interview.boot.exception.BookAlreadyReturnException;
import posmy.interview.boot.exception.BookNotAvailableException;
import posmy.interview.boot.exception.BookNotFoundException;
import posmy.interview.boot.model.Book;
import posmy.interview.boot.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@PostMapping
	public Book doCreate(@RequestBody Book book) {
		return repository.save(book);
	}
	
	@GetMapping
	public List<Book> doRetrieveAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{bookid}")
	public Book doRetrieveById(@PathVariable Long bookid) {
		return repository.findById(bookid).orElseThrow(() -> new BookNotFoundException(bookid));
	}
	
	@PutMapping("/{bookid}")
	public Book doSaveOrUpdate(@RequestBody Book book, @PathVariable Long bookid) {
		return repository.findById(bookid).map(b -> {
			b.setTitle(book.getTitle());
			b.setAuthor(book.getAuthor());
			return repository.save(b); 
		}).orElseGet(() -> {
			return repository.save(book);
		});
	}
	
	@PutMapping("/borrow/{bookid}/{userid}")
	public Book doBorrow(@PathVariable Long bookid, @PathVariable Long userid) {
		return repository.findById(bookid).map(b -> {
			if(b.getStatus().equals(BookStatus.AVAILABLE.toString())) {
				b.setUserid(userid);
				b.setStatus(BookStatus.BORROWED.toString());
				return repository.save(b);
			} else {
				throw new BookNotAvailableException(b.getTitle());
			}
		}).orElseThrow(() -> new BookNotFoundException(bookid));
	}
	
	@PutMapping("/return/{bookid}/{userid}")
	public Book doReturn(@PathVariable Long bookid, @PathVariable Long userid) {
		return repository.findById(bookid).map(b -> {
			if(b.getStatus().equals(BookStatus.BORROWED.toString())) {
				b.setUserid(null);
				b.setStatus(BookStatus.AVAILABLE.toString());
				return repository.save(b);
			} else {
				throw new BookAlreadyReturnException(b.getTitle());
			}
		}).orElseThrow(() -> new BookNotFoundException(bookid));
	}
	
	@DeleteMapping("/{bookid}")
	public void doDelete(@PathVariable Long bookid) {
		repository.deleteById(bookid);
	}
}
