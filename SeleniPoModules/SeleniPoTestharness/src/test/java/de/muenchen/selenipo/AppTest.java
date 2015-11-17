package de.muenchen.selenipo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import de.muenchen.selenipo.config.Config;
import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.CreatePage;
import de.muenchen.selenipo.po.ShowPage;

/**
 * Unit test for simple App.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class })
public class AppTest {

	@Autowired
	public BasePo po;

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testApp() throws InterruptedException {
		CreatePage createPage = new CreatePage(po);
		ShowPage showPage = createPage.createEntry("MyTitle", "MyNote");
		Thread.sleep(1000);
		assertEquals("MyTitle", showPage.idTitle.resolve().getText());
		assertEquals("MyNote", showPage.idNotes.resolve().getText());
	}
}
