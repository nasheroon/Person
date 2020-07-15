/**
 * 
 */
package posmy.interview.boot.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import posmy.interview.boot.enumeration.UserRoles;
import posmy.interview.boot.exception.UserNotAllowDeleteException;
import posmy.interview.boot.exception.UserNotFoundException;
import posmy.interview.boot.model.User;
import posmy.interview.boot.repository.UserRepository;

/**
 * @author Noor
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	private User doCreate(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping
	private List<User> doRetrieveAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{userid}")
	private User doRetrieveById(@PathVariable Long userid) {
		return repository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
	}
	
	@PutMapping("/{userid}")
	public User doSaveOrUpdate(@RequestBody User user, @PathVariable Long userid) {
		return repository.findById(userid).map(u -> {
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getFirstname());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setRolename(user.getRolename());
			return repository.save(u); 
		}).orElseGet(() -> {
			return repository.save(user);
		});
	}
	
	@DeleteMapping
	public void doDeleteAllUser() {
		repository.deleteAll();
	}
	
	@DeleteMapping("/{userid}")
	public void deDeleteByUserId(HttpServletRequest request, @PathVariable Long userid) {
		
		Principal principal = request.getUserPrincipal();
		String loginUsername = principal.getName();
		boolean isAllowDelete = true;
		
		User userLogin = repository.findByUsername(loginUsername).orElseThrow(() -> new UserNotFoundException(loginUsername));
		
		if(userLogin != null && userLogin.getRolename().equals(UserRoles.MEMBER.toString()) ) {
			if(userLogin.getUserid() != userid) {
				isAllowDelete = false;
			} 
		}
		
		if(isAllowDelete) {
			repository.deleteById(userid);
		} else {
			throw new UserNotAllowDeleteException(loginUsername, userLogin.getRolename());
		}
	}
}
