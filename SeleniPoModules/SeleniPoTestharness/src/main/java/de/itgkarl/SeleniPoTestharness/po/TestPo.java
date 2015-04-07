package de.itgkarl.SeleniPoTestharness.po;


public class TestPo extends BasePo {
	Control bEnter = control(by.link("bEnter"));

	public TestPo(PageObject po) {
		super(po);
	}

	public void doIt() {
		driver.get("localhost:3000/welcome/index");
		bEnter.click();
		driver.close();

	}

}
