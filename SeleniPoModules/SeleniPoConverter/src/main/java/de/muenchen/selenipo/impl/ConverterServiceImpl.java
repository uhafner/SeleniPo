package de.muenchen.selenipo.impl;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.xstream.XStream;

import de.muenchen.selenipo.ConverterService;
import de.muenchen.selenipo.Element;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.PoModel;

/**
 * Implementierung des PersistService der den Java Bean XMLEncoder benutzt.
 * Objekte müssen JavaBeans sein.
 * 
 * @author matthias
 *
 */
public class ConverterServiceImpl implements ConverterService {

	@Autowired
	private XStream xStream;

	@Override
	public final void persistToXml(final String path, final Object o) {
		try {
			File file = new File(path);
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(writer);
			// Writes the content to the file
			xStream.toXML(o, bWriter);
			writer.close();
			bWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public final Object loadFromXml(final String path) {
		Object o = null;
		// Creates a FileReader Object
		try {
			InputStream in = new FileInputStream(path);
			BufferedInputStream bIn = new BufferedInputStream(in);
			o = xStream.fromXML(bIn);
			bIn.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public PoModelImpl convertToImpl(PoModel poModel) {
		PoModelImpl poModelImpl = new PoModelImpl();
		for (PoGeneric poGeneric : poModel.getPoGenerics()) {
			PoGeneric tempPoGeneric = new PoGenericImpl();
			poModelImpl.getPoGenerics().add(tempPoGeneric);
			tempPoGeneric.setIdentifier(poGeneric.getIdentifier());
			

		}

		return null;
	}
}
