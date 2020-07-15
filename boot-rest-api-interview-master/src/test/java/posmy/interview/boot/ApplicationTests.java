package posmy.interview.boot;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import posmy.interview.boot.model.Book;
import posmy.interview.boot.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.util.HashMap;
import java.util.Map;;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("LIBRARIAN can create LIBRARIAN")
    void createLibrarian() {
    	// Create librarian
    	User user = new User("John","Travolta","john555","password","LIBRARIAN");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    }
    
    @Test
    @DisplayName("LIBRARIAN can create MEMBER")
    void createMember() {
        // Create member
        User user = new User("Ahmad","Khairul","ahmad","password","MEMBER");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    }
     
    @Test
    @DisplayName("LIBRARIAN can retrieve all users")
    void retrieveUsers() {
    
        var response = restTemplate.withBasicAuth("admin", "password").getForEntity("/users", String.class);
        
        assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
        
    }
     
    @Test
    @DisplayName("LIBRARIAN can retrieve a user")
    void retrieveUser() throws JSONException {
    	// Create member
        User user = new User("Shamsul","Khairul","shamsul","password","MEMBER");
        
        var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);
        
        assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
        
        JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("userid",json.getString("userid"));
        
        var response2 = restTemplate.withBasicAuth("admin", "password").getForEntity("/users/{userid}", String.class, pathParam);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    }
     
    @Test
    @DisplayName("LIBRARIAN can update a user")
    void updateUser() {
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("userid","3");
        
        User user = new User("Aisyah","Ghazali","aisyah","password","MEMBER");
        
        restTemplate.withBasicAuth("admin", "password").put("/users/{userid}", user, pathParam);
        
    }
    
    @Test
    @DisplayName("LIBRARIAN can delete a user")
    void deleteUser() throws JSONException {
    	
    	// Create member
        User user = new User("Ahmad","Khairul","ahmad","password","MEMBER");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);
    	
    	assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    	
    	JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("userid",json.getString("userid"));
        
        restTemplate.withBasicAuth("admin", "password").delete("/users/{userid}", pathParam);
        
    }
    
//    @Test
//    @DisplayName("LIBRARIAN can delete all user")
//    void deleteAllUser() {
//    	
//    	// Create member
//        User user = new User("Ahmad","Khairul","ahmad","password","MEMBER");
//    	
//    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);
//    	
//    	assertThat(response)
//	        .extracting(ResponseEntity::getStatusCode)
//	        .isEqualTo(OK);
//    	
//        restTemplate.withBasicAuth("admin", "password").delete("/users");
//
//    }

    @Test
    @DisplayName("MEMBER can retrieve all users")
    void mRetrieveUsers() {
    
        var response2 = restTemplate.withBasicAuth("aisyah", "password").getForEntity("/users", String.class);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
        
    }
     
    @Test
    @DisplayName("MEMBER can retrieve a user")
    void mRetrieveUser() throws JSONException {
    	// Create member
        User user = new User("Faisal","Khairul","faisal","password","MEMBER");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);
    	
    	assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    	
    	JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("userid",json.getString("userid"));
        
        var response2 = restTemplate.withBasicAuth("aisyah", "password").getForEntity("/users/{userid}", String.class, pathParam);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    }
    
    @Test
    @DisplayName("MEMBER can delete self")
    void deleteOwnAccount() throws JSONException {
    	// Create member
        User user = new User("Kamal","Ali","kamal","password","MEMBER");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/users", user, String.class);
    	
    	assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    	
    	JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("userid",json.getString("userid"));
        
        restTemplate.withBasicAuth("kamal", "password").delete("/users/{userid}", pathParam);
        
    }
    
    @Test
    @DisplayName("LIBRARIAN can create book")
    void createBook() {
    	// Create book
    	Book book = new Book("ABC Cooking","Joel Shimmer");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/books", book, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    }
    
    @Test
    @DisplayName("MEMBER cannot create book")
    void mCreateBook() {
        // Create book
    	Book book = new Book("ABC Dating","Joel Shimmer");
    	
    	var response = restTemplate.withBasicAuth("aisyah", "password").postForEntity("/books", book, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(FORBIDDEN);
    }
     
    @Test
    @DisplayName("LIBRARIAN can retrieve all books")
    void retrieveBooks() {
    
        var response = restTemplate.withBasicAuth("admin", "password").getForEntity("/books", String.class);
        
        assertThat(response)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
        
    }
     
    @Test
    @DisplayName("LIBRARIAN can retrieve a book")
    void retrieveBook() throws JSONException {
    	// Create book
    	Book book = new Book("Sonata of Cooking","Joel Shimmer");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/books", book, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    	
        JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid",json.getString("bookid"));
        
        var response2 = restTemplate.withBasicAuth("ehsan", "password").getForEntity("/books/{bookid}", String.class, pathParam);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    }
    
    @Test
    @DisplayName("MEMBER can retrieve all books")
    void mRetrieveBooks() {

    	var response2 = restTemplate.withBasicAuth("aisyah", "password").getForEntity("/books", String.class);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
        
    }
     
    @Test
    @DisplayName("MEMBER can retrieve a book")
    void mRetrieveBook() throws JSONException {
    	// Create book
    	Book book = new Book("Sonata of Dancing","Joel Shimmer");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/books", book, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    	
        JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid",json.getString("bookid"));
        
        var response2 = restTemplate.withBasicAuth("aisyah", "password").getForEntity("/books/{bookid}", String.class, pathParam);
        
        assertThat(response2)
	        .extracting(ResponseEntity::getStatusCode)
	        .isEqualTo(OK);
    }
     
    @Test
    @DisplayName("MEMBER can borrow a book")
    void mBorrowBook() {
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid","1");
        pathParam.put("userid","3");
        
        restTemplate.withBasicAuth("aisyah", "password").put("/books/{bookid}/{userid}", new Book(), pathParam);
        
    }
    
    @Test
    @DisplayName("MEMBER can return a book")
    void mReturneBook() {
    	Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid","1");
        pathParam.put("userid","3");
        
        restTemplate.withBasicAuth("aisyah", "password").put("/books/{bookid}/{userid}", new Book(), pathParam);
        
    }
    
    @Test
    @DisplayName("LIBRARIAN can update a book")
    void updateBook() {
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid","1");
        
        Book book = new Book("Guidelines Dating","Joel Shimmer");
        
        restTemplate.withBasicAuth("ehsan", "password").put("/books/{bookid}", book, pathParam);
        
    }
    
    
    @Test
    @DisplayName("LIBRARIAN can delete a book")
    void deleteBook() throws JSONException {
    	// Create book
    	Book book = new Book("Abby of Evil","Joel Shimmer");
    	
    	var response = restTemplate.withBasicAuth("admin", "password").postForEntity("/books", book, String.class);

        assertThat(response)
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(OK);
    	
        JSONObject json = new JSONObject(response.getBody());
        
        Map<String,String> pathParam = new HashMap<String, String>();
        pathParam.put("bookid",json.getString("bookid"));
        
        restTemplate.withBasicAuth("ehsan", "password").delete("/books/{bookid}", pathParam);
        
    }
    
}
