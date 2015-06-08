package de.muenchen.selenipo.config;

import org.springframework.context.annotation.Bean;

import de.muenchen.selenipo.HtmlParserService;
import de.muenchen.selenipo.impl.HtmlParserServiceImpl;

public class HtmlParserConfig {

	@Bean
	HtmlParserService htmlParserService() {
		return new HtmlParserServiceImpl();
	}

}
