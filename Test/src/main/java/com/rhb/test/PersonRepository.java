package com.rhb.test;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findOneByFirstName(String firstname);

	void deleteByFirstName(String firstname);

}
