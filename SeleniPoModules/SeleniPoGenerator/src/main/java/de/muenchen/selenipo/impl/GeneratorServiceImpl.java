package de.muenchen.selenipo.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DisplayTool;
import org.springframework.beans.factory.annotation.Autowired;

import de.muenchen.selenipo.GeneratorService;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.PoModel;

/**
 * Impl des GeneratorService Interface.
 * 
 * @author matthias
 *
 */
public class GeneratorServiceImpl implements GeneratorService {

	private static final Logger logger = Logger
			.getLogger(GeneratorServiceImpl.class);

	@Autowired
	VelocityEngine velocityEngine;

	@Override
	public Map<String, String> generatePageObjects(final PoModel poModel)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> generatePageObject(final PoGeneric poGeneric)
			throws IOException {
		/* next, get the Template */
		Template t = velocityEngine
				.getTemplate("de/muenchen/selenipo/poGeneric.vm");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("display", new DisplayTool());
		context.put("poGeneric", poGeneric);
		/* now render the template into a StringWriter */
		File file = new File("./test.txt");
		FileWriter out = new FileWriter(file);
		BufferedWriter w = new BufferedWriter(out);
		StringWriter writer = new StringWriter();
		t.merge(context, w);
		w.flush();
		/* show the World */
		return null;
	}

}
