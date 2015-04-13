package de.muenchen.selenipo.config;

import de.muenchen.selenipo.ByFactory;
import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Properties;

/**
 * Class with beandeclarations for springcontext.
 * 
 * @author matthias
 *
 */
@Configurable
public class Config {
	/**
	 * Name des PropertyFiles.
	 */
	public static final String CONFIG_FILE = "config.properties";
	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(Config.class);

	/**
	 * WebDriver for Selenium. Gets instantiated with a base URL located in the
	 * config.properties.
	 * 
	 * @return WebDriver
	 * @throws IOException
	 *             for properties input stream.
	 */
	@Bean(destroyMethod = "")
	public final WebDriver webDriver() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/" + CONFIG_FILE));
		String baseUrl = props.getProperty("webDriver.baseUrl");
		log.info("Loaded baseUrl for driver: [baseUrl = " + baseUrl + "].");
		// ToDo: Verschiedene Driver auswäjlbar machen?

		WebDriver driver = new FirefoxDriver();
		driver.get(baseUrl);
		return driver;
	}

	/**
	 * ByFactory.
	 * 
	 * @return ByFactory
	 */
	@Bean
	public final ByFactory byFactory() {
		return new ByFactory();
	}

	/**
	 * PageObject.
	 * 
	 * @return PageObject
	 */
	@Bean
	public final PageObject pageObject() {
		return new PageObject();
	}

	/**
	 * BasePo.
	 * 
	 * @return BasePo
	 */
	@Bean
	public final BasePo basePo() {
		return new BasePo();
	}

}