package posmy.interview.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import posmy.interview.boot.model.Book;
import posmy.interview.boot.model.User;
import posmy.interview.boot.repository.BookRepository;
import posmy.interview.boot.repository.UserRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, BookRepository bookRepo) {
    	String username = "admin";
    	String password = "password";
    	
    	System.out.println("Librarian Administrator");
    	System.out.println("username = \"admin\"");
    	System.out.println("password = \"password\"");
    	
        return args -> {
        	userRepo.save(new User("default", "user", username, password, "LIBRARIAN"));
        	userRepo.save(new User("Ehsan","Jaffar","ehsan","password","LIBRARIAN"));
        	userRepo.save(new User("Aisyah","Mahmud","aisyah","password","MEMBER"));
        	bookRepo.save(new Book("Guidelines to Crafting","Joel Shimmer"));
        	bookRepo.save(new Book("Guidelines to Camping","Joel Shimmer"));
        	bookRepo.save(new Book("Guidelines to Sewing","Joel Shimmer"));
        };
    }


}
