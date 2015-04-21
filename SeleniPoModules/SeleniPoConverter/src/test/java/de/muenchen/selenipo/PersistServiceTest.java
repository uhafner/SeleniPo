package de.muenchen.selenipo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.muenchen.selenipo.config.ConverterConfig;
import de.muenchen.selenipo.impl.persistanceModel.ElementImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoGenericImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;
import de.muenchen.selenipo.impl.persistanceModel.TransitionImpl;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersistServiceTest {

	@Autowired
	ConverterService persistService;

	@Test
	public void BaseTest() {
		String path = "test.xml";
		PoModel testModel = getTestPoGeneric();
		System.out.println(testModel);
		persistService.persistToXml(path, testModel);
		PoModel loaded = (PoModel) persistService.loadFromXml(path);
		Assert.assertEquals(testModel, loaded);
	}

	private PoModel getTestPoGeneric() {
		PoModelImpl model = new PoModelImpl();
		PoGeneric welcomePo = new PoGenericImpl("WelcomePo");
		PoGeneric listPo = new PoGenericImpl("listPo");
		model.getPoGenerics().add(welcomePo);
		model.getPoGenerics().add(listPo);
		Transition bEnter = new TransitionImpl(Selector.LINK, "bEnter", listPo);
		ElementImpl h1 = new ElementImpl(Selector.XPATH, "//h1");
		TransitionImpl index = new TransitionImpl(Selector.LINK, "ToDo-App",
				welcomePo);
		welcomePo.getTransitions().add(bEnter);
		welcomePo.getElements().add(h1);
		listPo.getTransitions().add(index);
		return model;
	}
}
