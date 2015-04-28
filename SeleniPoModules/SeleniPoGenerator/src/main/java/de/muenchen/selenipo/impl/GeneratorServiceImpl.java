package de.muenchen.selenipo.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DisplayTool;
import org.springframework.beans.factory.annotation.Autowired;

import de.muenchen.selenipo.GeneratorService;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.PoModel;
import de.muenchen.selenipo.Transition;

/**
 * Impl des GeneratorService Interface.
 * 
 * @author matthias
 *
 */
public class GeneratorServiceImpl implements GeneratorService {

	private static final Logger logger = Logger
			.getLogger(GeneratorServiceImpl.class);

	private static final String GENERATED_FOLDER_NAME = "generated";
	private static final String EDIT_FOLDER_NAME = "edit";

	@Autowired
	VelocityEngine velocityEngine;
	@Autowired
	DisplayTool display;

	@Override
	public Map<String, String> generatePageObjects(final PoModel poModel,
			final String rootFolder) throws IOException {
		Map<String, String> returnValue = new HashMap<String, String>();
		List<PoGeneric> poGenerics = poModel.getPoGenerics();
		for (PoGeneric poGeneric : poGenerics) {
			logger.debug(String.format("Starte Generierung für Po: [%s]",
					poGeneric.getIdentifier()));
			Map<String, String> poMap = generatePageObject(poGeneric,
					rootFolder);
			returnValue.putAll(poMap);
		}
		return returnValue;
	}

	@Override
	public Map<String, String> generatePageObject(final PoGeneric poGeneric,
			final String rootFolder) throws IOException {
		// Map to return
		Map<String, String> returnValue = new HashMap<String, String>();
		// Befülle liste mit unterschiedlichen DestinationPos für die Imports
		Set<PoGeneric> destinationPos = new HashSet<PoGeneric>();
		for (Transition transition : poGeneric.getTransitions()) {
			destinationPos.add(transition.getDestination());
		}

		// create a context and add data
		VelocityContext context = new VelocityContext();
		context.put("display", display);
		context.put("poGeneric", poGeneric);
		context.put("destionationPos", destinationPos);

		logger.debug(String.format("RootFolder: %s", rootFolder));
		logger.debug("-Erzeuge Generated PO:");
		// Generate Path
		final String packagePath = poGeneric.getPackageName().replaceAll("\\.",
				"/");
		logger.debug(String.format("-- packagePath: %s", packagePath));

		// Erzeuge Generated Po
		// Get Template
		Template tGenerated = velocityEngine
				.getTemplate("de/muenchen/selenipo/poGeneric.vm");
		final String wholePathGenerated = String.format(
				"%s/%s/%s/%sGenerated.java", rootFolder, GENERATED_FOLDER_NAME,
				packagePath, poGeneric.getIdentifier());
		Map<String, String> mapGenerated = writeFile(wholePathGenerated,
				context, tGenerated, poGeneric);

		// Erzeuge Edit Po
		// Get Template
		Template tEdit = velocityEngine
				.getTemplate("de/muenchen/selenipo/poEditable.vm");
		final String wholePathEdit = String.format("%s/%s/%s/%s.java",
				rootFolder, EDIT_FOLDER_NAME, packagePath,
				poGeneric.getIdentifier());
		writeFile(wholePathEdit, context, tEdit, poGeneric);

		returnValue.putAll(mapGenerated);
		return returnValue;
	}

	private Map<String, String> writeFile(String wholePath,
			VelocityContext context, Template t, PoGeneric poGeneric)
			throws IOException {
		Map<String, String> returnValue = new HashMap<String, String>();
		logger.debug(String.format("-- wholePath: %s", wholePath));
		Path path = Paths.get(wholePath);

		// Create File
		Files.createDirectories(path.getParent());
		File fileGenerated = new File(path.toString());

		// Write Data
		FileWriter out = new FileWriter(fileGenerated);
		BufferedWriter w = new BufferedWriter(out);
		StringWriter sw = new StringWriter();
		t.merge(context, w);
		t.merge(context, sw);

		returnValue.put(poGeneric.getIdentifier(), sw.toString());
		w.flush();
		sw.flush();
		/* show the World */
		return returnValue;
	}
}
