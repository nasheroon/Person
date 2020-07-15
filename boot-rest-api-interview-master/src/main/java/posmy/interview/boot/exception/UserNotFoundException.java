package posmy.interview.boot.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String name) {
        super("This user name: " + name + " is not found.");
    }
	
	public UserNotFoundException(Long userid) {
        super("This user id: " + userid + " is not found.");
    }
}

