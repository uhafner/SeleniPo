package de.itgkarl.SeleniPoTestharness.po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import de.itgkarl.SeleniPoTestharness.ByFactory;

/**
 * Generisches PageObject. Ältester Vater aller anderen PageObjects.
 * 
 * @see http://code.google.com/p/selenium/wiki/PageObjects
 * 
 * @author matthias.karl
 */
public class PageObject {

	@Autowired
	protected ByFactory by;
	@Autowired
	protected WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	protected List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	protected WebElement link(String text) {
		return find(By.linkText(text));
	}

	protected WebElement input(By by, String value) {
		try {
			WebElement input = find(by);
			input.sendKeys(value);
			return input;
		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Checks the checkbox.
	 */
	protected void check(WebElement e) {
		if (!e.isSelected())
			e.click();
	}

	/**
	 * Sets the state of the checkbox to the specified value.
	 */
	protected void check(WebElement e, boolean state) {
		if (e.isSelected() != state)
			e.click();
	}

	/**
	 * Checks the specified checkbox.
	 */
	protected void check(String locator) {
		check(find(by.checkbox(locator)));
	}

	/**
	 * Thread.sleep that masks exception.
	 */
	protected static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new Error(e);
		}
	}

	/**
	 * @param locator
	 *            Text, ID, or link.
	 */
	protected void clickLink(String locator) {
		find(by.link(locator)).click();
	}

	/**
	 * Picks up the last element that matches given selector.
	 */
	protected WebElement last(By selector) {
		find(selector); // wait until at least one is found

		// but what we want is the last one
		List<WebElement> l = driver.findElements(selector);
		return l.get(l.size() - 1);
	}

	/**
	 * Returns the first visible element that matches the selector.
	 * 
	 * @throws NoSuchElementException
	 *             if the element is not found.
	 * @see #getElement(By) if you don't want to see an exception
	 */
	protected WebElement find(By selector) {
		try {
			long endTime = System.currentTimeMillis() + 1000;
			while (System.currentTimeMillis() <= endTime) {
				WebElement e = driver.findElement(selector);
				if (isDisplayed(e))
					return e;

				for (WebElement f : driver.findElements(selector)) {
					if (isDisplayed(f))
						return f;
				}

				// give a bit more chance for the element to become visible
				sleep(100);
			}

			throw new NoSuchElementException("Unable to locate visible "
					+ selector + " in " + driver.getCurrentUrl());
		} catch (NoSuchElementException x) {
			// this is often the best place to set a breakpoint
			String msg = String.format("Unable to locate %s in %s\n\n%s",
					selector, driver.getCurrentUrl(), driver.getPageSource());
			throw new NoSuchElementException(msg, x);
		}
	}

	/**
	 * Consider stale elements not displayed.
	 */
	private boolean isDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		} catch (StaleElementReferenceException _) {
			return false;
		}
	}

	protected Control control(By... bys) {
		return new ControlBys(this, bys);
	}

	protected ByFactory getBy() {
		return by;
	}

}
