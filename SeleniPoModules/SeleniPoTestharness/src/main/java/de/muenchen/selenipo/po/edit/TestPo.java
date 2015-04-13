package de.muenchen.selenipo.po.edit;

import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.generated.TestPoGenerated;

public class TestPo extends TestPoGenerated {

	public TestPo(PageObject po) {
		super(po);
	}

	public void doIt() {
		driver.get("localhost:3000/welcome/index");
		bEnter.click();
		driver.close();

	}

	
}
