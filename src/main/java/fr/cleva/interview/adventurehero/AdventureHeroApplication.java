package fr.cleva.interview.adventurehero;

import com.google.common.primitives.Chars;
import fr.cleva.interview.adventurehero.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class AdventureHeroApplication {

	@Autowired
	CardService cardService;

	public static void main(String[] args) {


		SpringApplication.run(AdventureHeroApplication.class, args);
	}
}
