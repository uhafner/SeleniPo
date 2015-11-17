
package de.muenchen.selenipo.po;


public class CreatePage extends CreatePageGenerated {

	public CreatePage(PageObject po) {
		super(po);
	}

	public ShowPage createEntry(String title, String note){
		tfTodotitle.sendKeys(title);
		tfTodonotes.sendKeys(note);
		return clickBCreateTodo();
	}
	
}
