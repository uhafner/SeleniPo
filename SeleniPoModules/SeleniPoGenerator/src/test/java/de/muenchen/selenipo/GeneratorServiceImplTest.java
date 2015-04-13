package de.muenchen.selenipo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.muenchen.selenipo.config.Config;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class GeneratorServiceImplTest {

	@Autowired
	public GeneratorService generatorService;

	@Test
	public void testGenerationSinglePo() {
		PoGeneric po = getTestPoGeneric();
		System.out.println(po);
	}

	/**
	 * Generates a dummy PoGeneric for testcases.
	 * 
	 * @return
	 */
	private PoGeneric getTestPoGeneric() {
		PoGeneric welcomePo = new PoGeneric("WelcomePo");
		PoGeneric listPo = new PoGeneric("listPo");
		Transition bEnter = new Transition(Selector.LINK, "bEnter", listPo);
		Element h1 = new Element(Selector.XPATH, "//h1");
		Transition index = new Transition(Selector.LINK, "ToDo-App", welcomePo);
		welcomePo.getTransitions().add(bEnter);
		welcomePo.getElements().add(h1);
		listPo.getTransitions().add(index);
		return welcomePo;
	}
}
