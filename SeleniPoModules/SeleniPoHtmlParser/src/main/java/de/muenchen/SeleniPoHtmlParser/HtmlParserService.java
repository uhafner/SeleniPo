package de.muenchen.SeleniPoHtmlParser;

import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.Selector;

public interface HtmlParserService {

	PoGeneric parseElementsFromHtmlForType(String html, Selector type);
}
