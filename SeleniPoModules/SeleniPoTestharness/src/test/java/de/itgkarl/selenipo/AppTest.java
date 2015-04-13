package de.itgkarl.selenipo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.itgkarl.selenipo.config.Config;
import de.itgkarl.selenipo.po.BasePo;
import de.itgkarl.selenipo.po.edit.TestPo;

/**
 * Unit test for simple App.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
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
