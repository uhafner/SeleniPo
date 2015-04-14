package de.muenchen.selenipo.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.PoModel;

public class PoModelFx implements PoModel {

	ObservableList<PoGenericFx> poGenericsFx = FXCollections
			.observableArrayList();

	public PoModelFx() {
		super();
	}
	
	@Override
	public List<PoGeneric> getPoGenerics() {
		List<PoGeneric> poGen = new ArrayList<PoGeneric>();
		for (PoGenericFx poGenericFx : poGenericsFx) {
			poGen.add(poGenericFx);
		}
		return poGen;
	}

	public ObservableList<PoGenericFx> getPoGenericsFx() {
		return poGenericsFx;
	}

	public void setPoGenericsFx(ObservableList<PoGenericFx> poGenericsFx) {
		this.poGenericsFx = poGenericsFx;
	}

}
