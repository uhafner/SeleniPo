package de.muenchen.SeleniPoHtmlParser.config;

import org.springframework.context.annotation.Bean;

import de.muenchen.SeleniPoHtmlParser.HtmlParserService;
import de.muenchen.SeleniPoHtmlParser.impl.HtmlParserServiceImpl;

public class HtmlParserConfig {

	@Bean
	HtmlParserService htmlParserService() {
		return new HtmlParserServiceImpl();
	}

}
