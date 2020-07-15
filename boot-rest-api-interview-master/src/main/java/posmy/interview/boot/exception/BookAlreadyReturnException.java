package posmy.interview.boot.exception;

@SuppressWarnings("serial")
public class BookAlreadyReturnException extends RuntimeException {

	public BookAlreadyReturnException(String title) {
        super("This book title: " + title + " is already return");
    }
}

