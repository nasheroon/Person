package posmy.interview.boot.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String title) {
        super("This book title: " + title + " is not found.");
    }
	
	public BookNotFoundException(Long bookid) {
        super("This book id: " + bookid+ " is not found.");
    }
}

