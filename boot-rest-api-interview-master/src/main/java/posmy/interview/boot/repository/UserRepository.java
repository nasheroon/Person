/**
 * 
 */
package posmy.interview.boot.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import posmy.interview.boot.model.User;

/**
 * @author Noor
 *
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param name
	 * @return
	 */
	Optional<User> findByFirstname(String firstname);

	/**
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

}
