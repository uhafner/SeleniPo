package de.muenchen.selenipo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.muenchen.selenipo.config.ConverterConfig;
import de.muenchen.selenipo.config.GeneratorConfig;
import de.muenchen.selenipo.impl.persistanceModel.ElementImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoGenericImpl;
import de.muenchen.selenipo.impl.persistanceModel.TransitionImpl;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GeneratorConfig.class, loader = AnnotationConfigContextLoader.class)
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
		PoGeneric welcomePo = new PoGenericImpl("WelcomePo");
		PoGeneric listPo = new PoGenericImpl("listPo");
		Transition bEnter = new TransitionImpl(Selector.LINK, "bEnter", listPo);
		ElementImpl h1 = new ElementImpl(Selector.XPATH, "//h1");
		TransitionImpl index = new TransitionImpl(Selector.LINK, "ToDo-App",
				welcomePo);
		welcomePo.getTransitions().add(bEnter);
		welcomePo.getElements().add(h1);
		listPo.getTransitions().add(index);
		return welcomePo;
	}
}
