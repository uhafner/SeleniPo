
package de.muenchen.selenipo.po.generated.testTemp;

import de.muenchen.selenipo.po.BasePo;
import de.muenchen.selenipo.po.Control;
import de.muenchen.selenipo.po.PageObject;
import de.muenchen.selenipo.po.edit.testTemp.WelcomePo;

public class listPoGenerated extends BasePo {

	public final Control ToDo-App = control(by.link("ToDo-App"));

	public listPoGenerated(PageObject po) {
		super(po);
	}
 
	
	public WelcomePo clickToDo-App() {
		return new WelcomePo(this);
	}
	
 
	/**
	 * Get the Control for the Transition ToDo-App.
	 * @return ToDo-App - Transition
	 */
	public Control getToDo-App() {
		return ToDo-App;
	}
	

}
