package de.velocityTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 *
 */
@Service
public class App {

	public String outputTemplateBase() throws IOException {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		ve.init();
		/* next, get the Template */
		Template t = ve.getTemplate("de/velocityTest/helloworld.vm");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("name", "World");
		/* now render the template into a StringWriter */
		File file = new File("./test.txt");
		FileWriter out = new FileWriter(file);
		BufferedWriter w = new BufferedWriter(out);
		StringWriter writer = new StringWriter();
		t.merge(context, w);
		w.flush();
		/* show the World */
		return w.toString();
	}

	public String outputTemplatePetMail() throws IOException {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		ve.init();
		/* next, get the Template */
		Template t = ve.getTemplate("de/velocityTest/petEmail.vm");
		/* create a context and add data */
		ArrayList list = new ArrayList();
		Map map = new HashMap();
		map.put("name", "horse");
		map.put("price", "00.00");
		list.add(map);

		map = new HashMap();
		map.put("name", "dog");
		map.put("price", "9.99");
		list.add(map);
		map = new HashMap();
		map.put("name", "bear");
		map.put("price", ".99");
		list.add(map);
		/* add that list to a VelocityContext */
		VelocityContext context = new VelocityContext();
		context.put("petList", list);
		
		File file = new File("./petMail.txt");
		FileWriter out = new FileWriter(file);
		BufferedWriter w = new BufferedWriter(out);
		StringWriter writer = new StringWriter();
		t.merge(context, w);
		w.flush();
		/* show the World */
		return w.toString();
	}

	public static void main(String[] args) throws IOException {

		System.out.println("MAINTEST_APP");
	}

}
