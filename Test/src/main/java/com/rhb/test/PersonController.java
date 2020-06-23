package com.rhb.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository repository;
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return repository.save(person);
	}
	
	@GetMapping
	public List<Person> list(){
		return repository.findAll();
	}
	
	@GetMapping("/getPersonByFirstName/{firstname}")
	public Person getPersonByFirstName(@PathVariable String firstname) {
		return repository.findOneByFirstName(firstname).orElseThrow(() -> new PersonNotFoundException(firstname));
	}
	
	@PutMapping("/{firstname}")
	public Person saveOrUpdate(@RequestBody Person person, @PathVariable String firstname) {
		return repository.findOneByFirstName(firstname).map(p -> {
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setEmail(person.getEmail());
			return repository.save(p);
		}).orElseGet(() -> {
			return repository.save(person);
		});
	}
	
	@DeleteMapping("/{firstname}")
	public void delete(@PathVariable String firstname) {
		repository.deleteByFirstName(firstname);
	}
}
