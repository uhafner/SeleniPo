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
		PoGeneric poGeneric = new PoGenericImpl();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("a");
		for (Element element : elements) {
			if (element.hasAttr("id")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier("a", element), Selector.LINK,
						element.attr("id"));
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasText()) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier("a", element), Selector.LINK,
						element.text());
				poGeneric.getElements().add(createdElement);
			}

			else if (element.hasAttr("title")) {
				de.muenchen.selenipo.Element createdElement = createElement(
						genIdentefier("a", element), Selector.LINK,
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
			suffix = element.text();
		} else if (element.hasAttr("id")) {
			suffix = element.attr("id");
		} else if (element.hasAttr("name")) {
			suffix = element.attr("name");
		} else if (element.hasAttr("title")) {
			suffix = element.attr("title");
		} else {
			RandomStringUtils.randomAlphanumeric(6);
		}

		return returnIdentefier + prepareIdentefierString(suffix);
	}

	private String prepareIdentefierString(String str) {
		return WordUtils.capitalize(str).replaceAll("\\s+", "")
				.replaceAll("[^a-zA-Z]+", "");
	}
}
