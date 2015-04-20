package de.muenchen.selenipo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import de.muenchen.selenipo.config.Config;
import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.edit.TestPo;

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
	 */
	@Test
	public void testApp() {
		TestPo p = new TestPo(po);
		p.doIt();
		assertTrue(true);

	}
}
