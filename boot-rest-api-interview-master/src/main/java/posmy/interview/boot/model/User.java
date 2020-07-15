/**
 * 
 */
package posmy.interview.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Noor
 *
 */
@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long userid;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
    private String rolename;
    
	public User() {
		super();
	}   
    
	public User(String firstname, String lastname, String username, String password, String rolename) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rolename = rolename;
	}

}
