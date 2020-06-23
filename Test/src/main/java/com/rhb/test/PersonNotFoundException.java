package com.rhb.test;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String firstname) {
        super("Person firstname not found : " + firstname);
    }
}
