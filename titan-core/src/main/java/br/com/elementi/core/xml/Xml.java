package br.com.elementi.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.internal.oxm.record.SAXUnmarshaller;
import org.eclipse.persistence.internal.oxm.record.XMLReader;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBUnmarshaller;
import org.eclipse.persistence.oxm.XMLUnmarshaller;

import com.google.common.collect.Maps;

public class Xml {

	public static void write(XmlFile response, String path) throws Exception {
		// Map<String, Object> properties = Maps.newHashMap();
		// properties.put(JAXBContextProperties.NAMESPACE_PREFIX_MAPPER, new
		// MyNamespacePrefixMapper());
		// JAXBContext jc = JAXBContextFactory.createContext(new Class<?>[] {
		// XmlFileResponse.class }, properties);

		// JAXBContext jc =
		// org.eclipse.persistence.jaxb.JAXBContext.newInstance(XmlFileResponse.class);

		Map<String, Object> properties = Maps.newHashMap();
		JAXBContext jc = JAXBContextFactory.createContext(new Class<?>[] { XmlFile.class }, properties);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// marshaller.setProperty(JAXBContextProperties.NAMESPACE_PREFIX_MAPPER,
		// new MyNamespacePrefixMapper());
		// marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
		// "urn:bvmf.052.01.xsd bvmf.052.01.xsd");
		marshaller.marshal(response, new FileOutputStream(path));

	}

	public static XmlFile read(String filename) throws Exception {
		/*Map<String, Object> properties = Maps.newHashMap();
		JAXBContext jc = JAXBContextFactory.createContext(new Class<?>[] { XmlFileReceive.class }, properties);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource source = new StreamSource(new FileInputStream(new File(filename)));
		setNamespaceAwareToFalse(unmarshaller);
		XmlFileReceive convert = (XmlFileReceive) unmarshaller.unmarshal(source);
		*/
		Map<String, Object> properties = Maps.newHashMap();
		JAXBContext jc = JAXBContextFactory.createContext(new Class<?>[] { XmlFile.class }, properties);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource source = new StreamSource(new FileInputStream(new File(filename)));
		setNamespaceAwareToFalse(unmarshaller);
		XmlFile convert = (XmlFile) unmarshaller.unmarshal(source);
		return convert;
	}

	private static void setNamespaceAwareToFalse(Unmarshaller unmarshaller) throws Exception {
		SAXUnmarshaller saxUnmarshaller = getSaxUnmarshaller(unmarshaller);
		XMLReader xmlReader = getXMLReader(saxUnmarshaller);
		xmlReader.setNamespaceAware(false);
	}

	private static XMLReader getXMLReader(SAXUnmarshaller saxUnmarshaller) throws Exception {
		Method getXMLReader = SAXUnmarshaller.class.getDeclaredMethod("getXMLReader");
		getXMLReader.setAccessible(true);
		XMLReader xmlReader = (XMLReader) getXMLReader.invoke(saxUnmarshaller);
		return xmlReader;
	}

	private static SAXUnmarshaller getSaxUnmarshaller(Unmarshaller unmarshaller) throws Exception {
		Field field = XMLUnmarshaller.class.getSuperclass().getDeclaredField("platformUnmarshaller");
		field.setAccessible(true);
		SAXUnmarshaller saxUnmarshaller = (SAXUnmarshaller) field.get(((XMLUnmarshaller) ((JAXBUnmarshaller) unmarshaller).getXMLUnmarshaller()));
		return saxUnmarshaller;
	}

}
