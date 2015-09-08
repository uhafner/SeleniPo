package de.muenchen.selenipo.po;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;

public class ShowPageGenerated extends BasePo {

	public final Control idTitle = control(by.id("title"));
	public final Control idNotes = control(by.id("note"));

	public ShowPageGenerated(PageObject po) {
		super(po);
	}
}
