package org.SeleniPoTestharness.po;

/**
 * BasePo ist Vater aller anderen PageObjects. Beinhaltet die Logik zum Einstieg
 * in die Anwendung. Ist das einzige Po das ohne übergabe eines existierenden PO
 * erzeugt werden kann.
 * 
 * @author matthias.karl
 *
 */
public class BasePo extends PageObject {

	public BasePo(PageObject po) {
		super();
		this.driver = po.getDriver();
		this.by = po.getBy();
	}

	public BasePo() {
		super();
	}

}
