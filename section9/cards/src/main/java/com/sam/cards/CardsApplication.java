package com.sam.cards;

import com.sam.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "SamBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Samridhi Parihar",
						email = "tutor@eazybytes.com",
						url = "abc.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "abc.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SamBank Cards microservice REST API Documentation",
				url = "abc.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
