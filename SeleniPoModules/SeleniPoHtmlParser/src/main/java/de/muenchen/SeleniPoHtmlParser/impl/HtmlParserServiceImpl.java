package de.muenchen.SeleniPoHtmlParser.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.muenchen.SeleniPoHtmlParser.HtmlParserService;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.impl.persistanceModel.ElementImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoGenericImpl;

public class HtmlParserServiceImpl implements HtmlParserService {
	private static final Logger logger = Logger
			.getLogger(HtmlParserServiceImpl.class);

	@Override
	public PoGeneric parseElementsFromHtmlForType(String html, Selector type) {
		PoGeneric poGeneric = null;
		switch (type) {
		case LINK:
			poGeneric = parseElementsFromHtmlForLink(html);
			break;
		case INPUT:
			poGeneric = parseElementsFromHtmlForInput(html);
			break;
		case BUTTON:
			poGeneric = parseElementsFromHtmlForButton(html);
			break;

		default:
			logger.warn(String.format("Der Selector %s wird nicht unterstützt",
					type));
			break;
		}
		return poGeneric;
	}

	/**
	 * Sucht nach allen Links für den vorhanden ist: id or text or title
	 * 
	 * @param html
	 * @return
	 */
	private PoGeneric parseElementsFromHtmlForLink(String html) {
		final String PREFIX = "a";
		PoGeneric poGeneric = new PoGenericImpl();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("a");
		for (Element element : elements) {
			if (element.hasAttr("id")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.LINK,
						element.attr("id"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasText()) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.LINK,
						element.text());
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("title")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.LINK,
						element.attr("title"));
				poGeneric.getElements().add(createdElement);
			}
		}

		return poGeneric;
	}

	private PoGeneric parseElementsFromHtmlForInput(String html) {
		final String PREFIX = "i";
		PoGeneric poGeneric = new PoGenericImpl();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("textarea,input,select");
		for (Element element : elements) {
			if (element.hasAttr("id")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.INPUT,
						element.attr("id"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("name")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.INPUT,
						element.attr("name"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("value")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.INPUT,
						element.attr("value"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("placeholder")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier("i", element), Selector.INPUT,
						element.attr("placeholder"));
				poGeneric.getElements().add(createdElement);
			}

		}

		return poGeneric;
	}

	private PoGeneric parseElementsFromHtmlForButton(String html) {
		final String PREFIX = "b";
		PoGeneric poGeneric = new PoGenericImpl();
		Document doc = Jsoup.parse(html);
		Elements elementsInput = doc
				.select("input[type=\"submit\"],input[type=\"reset\"],input[type=\"image\"],input[type=\"button\"]");
		for (Element element : elementsInput) {
			if (element.hasAttr("id")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("id"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("name")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("name"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("value")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("value"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("title")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("title"));
				poGeneric.getElements().add(createdElement);
			} else if (element.hasAttr("type")
					&& element.attr("type").equals("image")) {
				if (element.hasAttr("alt")) {
					de.muenchen.selenipo.Element createdElement = createElement(
							genIdentefier(PREFIX, element), Selector.BUTTON,
							element.attr("alt"));
					poGeneric.getElements().add(createdElement);
				}
			}

		}

		Elements elementsButton = doc.select("button");
		for (Element element : elementsButton) {
			if (element.hasAttr("id")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("id"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("value")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("value"));
				poGeneric.getElements().add(createdElement);
			} else if (element.hasText()) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.text());
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("title")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier(PREFIX, element), Selector.BUTTON,
						element.attr("title"));
				poGeneric.getElements().add(createdElement);
			}

		}

		return poGeneric;
	}

	private de.muenchen.selenipo.Element createElement(String identifier,
			Selector type, String locator) {
		de.muenchen.selenipo.Element elem = new ElementImpl(identifier, type,
				locator);
		return elem;
	}

	private String genIdentefier(String prefix, Element element) {
		String returnIdentefier = prefix;
		String suffix = "";
		if (element.hasText()) {
			suffix = prepareIdentefierString(element.text());
		}
		if (suffix.equals("") && element.hasAttr("value")) {
			suffix = prepareIdentefierString(element.attr("value"));
		}
		if (suffix.equals("") && element.hasAttr("id")) {
			suffix = prepareIdentefierString(element.attr("id"));
		}
		if (suffix.equals("") && element.hasAttr("name")) {
			suffix = prepareIdentefierString(element.attr("name"));
		}
		if (suffix.equals("") && element.hasAttr("title")) {
			suffix = prepareIdentefierString(element.attr("title"));
		}

		if (suffix.equals("")) {
			suffix = RandomStringUtils.randomAlphanumeric(6);
		}

		return returnIdentefier + suffix;
	}

	private String prepareIdentefierString(String str) {
		return WordUtils.capitalize(str).replaceAll("\\s+", "")
				.replaceAll("[^a-zA-Z]+", "");
	}
}
