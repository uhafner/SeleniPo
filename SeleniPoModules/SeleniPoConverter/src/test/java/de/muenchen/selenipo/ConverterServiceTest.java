package de.muenchen.selenipo;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import javafx.collections.ObservableList;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AssertThrows;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.muenchen.selenipo.config.ConverterConfig;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.impl.fxModel.PoGenericFx;
import de.muenchen.selenipo.impl.fxModel.PoModelFx;
import de.muenchen.selenipo.impl.fxModel.TransitionFx;
import de.muenchen.selenipo.impl.persistanceModel.ElementImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoGenericImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;
import de.muenchen.selenipo.impl.persistanceModel.TransitionImpl;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterConfig.class, loader = AnnotationConfigContextLoader.class)
public class ConverterServiceTest {

	@Autowired
	ConverterService converterService;

	@Test
	public void saveAndLoad() {
		File file = new File("test.xml");
		PoModel testModel = getTestPoGeneric();
		converterService.persistToXml(file, testModel);
		PoModel loaded = (PoModel) converterService.loadFromXml(file);
		assertEquals(testModel, loaded);
	}

	@Test
	public void convertToFx() {
		PoModelImpl testModel = getTestPoGeneric();
		PoModelFx fxModel = converterService.convertToFxModel(testModel);

		Assert.assertThat(fxModel, CoreMatchers.instanceOf(PoModelFx.class));

		List<PoGeneric> poGenerics = testModel.getPoGenerics();
		ObservableList<PoGenericFx> poGenericsFx = fxModel.getPoGenericsFx();
		Assert.assertEquals(poGenerics.size(), poGenericsFx.size());

		for (int i = 0; i < poGenerics.size(); i++) {
			PoGeneric poGeneric = poGenerics.get(i);
			PoGenericFx poGenericFx = poGenericsFx.get(i);
			Assert.assertThat(poGenericFx,
					CoreMatchers.instanceOf(PoGenericFx.class));
			Assert.assertEquals(poGeneric.getIdentifier(),
					poGenericFx.getIdentifier());

			List<Element> elements = poGeneric.getElements();
			List<Element> elementsFx = poGenericFx.getElements();
			Assert.assertEquals(elements.size(), elementsFx.size());

			for (int j = 0; j < elements.size(); j++) {
				Element element = elements.get(j);
				Element elementFx = elementsFx.get(j);
				Assert.assertThat(elementFx,
						CoreMatchers.instanceOf(ElementFx.class));
				Assert.assertEquals(element.getIdentifier(),
						elementFx.getIdentifier());
				Assert.assertEquals(element.getLocator(),
						elementFx.getLocator());
				Assert.assertEquals(element.getType(), elementFx.getType());
			}

			List<Transition> transitions = poGeneric.getTransitions();
			List<Transition> transitionsFx = poGenericFx.getTransitions();
			Assert.assertEquals(transitions.size(), transitionsFx.size());

			for (int k = 0; k < elements.size(); k++) {
				Element transition = transitions.get(k);
				Element transitionFx = transitionsFx.get(k);
				Assert.assertThat(transitionFx,
						CoreMatchers.instanceOf(TransitionFx.class));
				Assert.assertEquals(transition.getIdentifier(),
						transitionFx.getIdentifier());
				Assert.assertEquals(transition.getLocator(),
						transitionFx.getLocator());
				Assert.assertEquals(transition.getType(),
						transitionFx.getType());
			}
		}
	}

	private PoModelImpl getTestPoGeneric() {
		PoModelImpl model = new PoModelImpl();
		PoGeneric welcomePo = new PoGenericImpl("WelcomePo");
		PoGeneric listPo = new PoGenericImpl("listPo");
		model.getPoGenerics().add(welcomePo);
		model.getPoGenerics().add(listPo);
		Transition bEnter = new TransitionImpl("EnterButton", Selector.LINK,
				"bEnter", listPo);
		ElementImpl h1 = new ElementImpl("hi", Selector.XPATH, "//h1");
		TransitionImpl index = new TransitionImpl("ToDo-App", Selector.LINK,
				"ToDo-App", welcomePo);
		welcomePo.getTransitions().add(bEnter);
		welcomePo.getElements().add(h1);
		listPo.getTransitions().add(index);
		return model;
	}
}
