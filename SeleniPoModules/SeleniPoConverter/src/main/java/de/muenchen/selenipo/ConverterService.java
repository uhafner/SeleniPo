package de.muenchen.selenipo;

import java.io.File;

import de.muenchen.selenipo.impl.fxModel.PoGenericFx;
import de.muenchen.selenipo.impl.fxModel.PoModelFx;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;

/**
 * Service zum persistieren einens objektes im XML Format.
 * 
 * @author matthias
 *
 */
public interface ConverterService {

	/**
	 * Persistiert ein Objekt im XML Format.
	 * 
	 * @param o
	 *            Objekt das Serialisiert werden soll.
	 */
	void persistToXml(File file, Object o);

	/**
	 * 
	 * @param path
	 * @param type
	 *            Type der geladen werden soll
	 * @return
	 */
	Object loadFromXml(File File);

	PoModelImpl convertToImpl(PoModel poModel);

	PoModelFx convertToFxModel(PoModel poModel);

	PoGenericFx convertToPoGenericFx(PoGeneric poGeneric);

}
