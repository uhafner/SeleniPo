package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.velocityTest.App;

@Configuration
public class Config {

	@Bean
	public App app(){
		return new App();
	}
	
}
