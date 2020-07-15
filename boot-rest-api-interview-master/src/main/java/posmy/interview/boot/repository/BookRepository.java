package posmy.interview.boot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import posmy.interview.boot.model.Book;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}
