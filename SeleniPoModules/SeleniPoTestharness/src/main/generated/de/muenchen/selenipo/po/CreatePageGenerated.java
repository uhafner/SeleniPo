
package de.muenchen.selenipo.po;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.ShowPage;

public class CreatePageGenerated extends BasePo {

	public final Control tfTodotitle = control(by.textField("todo_title"));
	public final Control tfTodonotes = control(by.textField("todo_notes"));
	public final Control bCreateTodo = control(by.button("commit"));

	public CreatePageGenerated(PageObject po) {
		super(po);
	}

	public ShowPage clickBCreateTodo() {
		bCreateTodo.click();
		return new ShowPage(this);
	}
}
