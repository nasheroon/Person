package posmy.interview.boot.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
    	
    	Contact contact = new Contact(
    			"Noor Ehsan", 
    			"http://localhost:8080", 
    			"nasheroon@gmail.com"
    	);
    	
    	@SuppressWarnings("rawtypes")
		List<VendorExtension> vendorExtensions = new ArrayList<>();
    	
    	ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Library",
                "1.0",
                "Terms of service",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", 
                vendorExtensions
    			);
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("posmy.interview.boot.controller"))
                .paths(regex("/api.*"))
                .build();
    }

}