package de.muenchen.selenipo;

import java.io.IOException;
import java.util.Map;

import de.muenchen.selenipo.impl.persistanceModel.PoGenericImpl;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;

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
	Map<String, String> generatePageObjects(PoModel poModel) throws IOException;

	/**
	 * Generates a singel pageObject frim a PoGeneric.
	 * 
	 * @param poGeneric
	 *            poGeneric
	 * @return String - Generated String
	 */
	Map<String, String> generatePageObject(PoGeneric poGeneric)
			throws IOException;

}
