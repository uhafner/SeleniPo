package de.muenchen.selenipo;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	Logger log = Logger.getLogger(App.class);

	public void performLo() {
		log.info("HelloWorld");

	}

	public static void main(String[] args) {
		App a = new App();
		a.performLo();
	}
}
