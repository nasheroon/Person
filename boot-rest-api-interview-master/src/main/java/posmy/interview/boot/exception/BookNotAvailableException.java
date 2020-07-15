package posmy.interview.boot.exception;

@SuppressWarnings("serial")
public class BookNotAvailableException extends RuntimeException {

	public BookNotAvailableException(String title) {
        super("This book title: " + title + " is not available");
    }
}

