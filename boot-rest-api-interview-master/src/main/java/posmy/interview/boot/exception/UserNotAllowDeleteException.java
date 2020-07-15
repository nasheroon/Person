/**
 * 
 */
package posmy.interview.boot.exception;

/**
 * @author Noor
 *
 */
@SuppressWarnings("serial")
public class UserNotAllowDeleteException extends RuntimeException {

	public UserNotAllowDeleteException(String username, String userrole) {
		super("This user: " + username + " is a " + userrole + " is not allowed to delete other user");
	}
}
