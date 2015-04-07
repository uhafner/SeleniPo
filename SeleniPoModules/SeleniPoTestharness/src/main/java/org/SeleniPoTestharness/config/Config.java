package org.SeleniPoTestharness.config;

import org.SeleniPoTestharness.ByFactory;
import org.SeleniPoTestharness.po.BasePo;
import org.SeleniPoTestharness.po.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class Config {

	@Bean(destroyMethod = "")
	public WebDriver webDriver() {
		// ToDo: Verschiedene Driver auswäjlbar machen?
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Bean
	public ByFactory byFactory() {
		return new ByFactory();
	}

	@Bean
	public PageObject pageObject() {
		return new PageObject();
	}

	@Bean
	public BasePo basePo() {
		return new BasePo();
	}

}