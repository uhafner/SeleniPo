package de.muenchen.selenipo;

import java.util.Map;

import de.muenchen.selenipo.impl.PoGenericImpl;
import de.muenchen.selenipo.impl.PoModelImpl;

/**
 * Service der die Funktionalität zum Generieren von PageObjecrts bereitstellt.
 * 
 * @author matthias
 *
 */
public interface GeneratorService {

	/**
	 * Generates all pageObjects of a PoModel.
	 * 
	 * @param poModel
	 *            PoModel
	 * @return PoModel
	 */
	Map<String, String> generatePageObjects(PoModel poModel);

	/**
	 * Generates a singel pageObject frim a PoGeneric.
	 * 
	 * @param poGeneric
	 *            poGeneric
	 * @return String - Generated String
	 */
	Map<String, String> generatePageObject(PoGeneric poGeneric);

}
