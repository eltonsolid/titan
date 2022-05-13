package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.hamcrest.core.IsNot;
import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.exception.NotAllowException;

public class RegexTest {

	@Test
	public void testBeanAction() throws Exception {
		String beanAction = "#{Controller.action}";
		Matcher action = Regex.beanAction(beanAction);
		action.find();
		assertEquals(beanAction, action.group());
	}

	@Test
	public void testBeanActionFail() throws Exception {
		String beanAction = "#{Controller._action}";
		Matcher action = Regex.beanAction(beanAction);
		assertFalse(action.find());
	}

	@Test
	public void testPlaque() throws Exception {
		String plaque = "KPO1171";
		Matcher matcher = Regex.plaque(plaque);
		assertTrue(matcher.matches());
	}

	@Test
	public void testPlaqueFail() throws Exception {
		String plaque = "KPO171";
		Matcher matcher = Regex.plaque(plaque);
		assertFalse(matcher.matches());
	}

	@Test
	public void testPack() throws Exception {
		String pack = "org.eltonsolid.core.entity";
		assertTrue(Regex.pack(pack).find());
	}

	@Test
	public void testProject() throws Exception {
		String project = "C:/something/somewere/titan-core/META-INF/domain/domain.xml";
		assertTrue(Regex.project(project).find());
	}

	@Test
	public void testEar() throws Exception {
		String value = "C:/icadx-business-ear.ear/icadx-service-metadata-0.0.1.jar/";
		assertTrue(Regex.ear(value).find());
	}

	@Test
	public void testJar() throws Exception {
		String value = "C:/icadx-business-ear.ear/icadx-service-metadata-0.0.1.jar/";
		assertTrue(Regex.jar(value).find());
	}

	@Test
	public void testClassFile() throws Exception {
		String classFile = "C:/something/somewere/titan-core/META-INF/domain.class";
		assertTrue(Regex.classFile(classFile));
	}

	@Test
	public void testClassFileWithInnerClass() throws Exception {
		String classFile = "C:/something/somewere/titan-core/META-INF/domain$1.class";
		assertFalse(Regex.classFile(classFile));
	}

	@Test
	public void testproperties() throws Exception {
		String path = "C:/something/somewere/titan-core/META-INF/domain.properties";
		assertTrue(Regex.properties(path));
	}

	@Test
	public void testpropertiesFail() throws Exception {
		String path = "C:/something/somewere/titan-core/META-INF/domain.propertie";
		assertFalse(Regex.properties(path));
	}

	@Test
	public void testPathForClass() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.class.Domain.class");
		assertEquals("org/eltonsolid/class/Domain.class", pathFormated);
	}

	@Test
	public void testPathForClassFail() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.class.Domain.classe");
		assertThat("org/eltonsolid/class/Domain.classe", IsNot.not(pathFormated));
	}

	@Test
	public void testPathForpropertiess() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.class.properties.Domain.properties");
		assertEquals("org/eltonsolid/class/properties/Domain.properties", pathFormated);
	}

	@Test
	public void testPathForpropertiessFail() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.class.properties.Domain.propertiess");
		assertThat("org/eltonsolid/class/properties/Domain.propertiess", IsNot.not(pathFormated));
	}

	@Test
	public void testPathForXml() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.xml.class.properties.Domain.xml");
		assertEquals("org/eltonsolid/xml/class/properties/Domain.xml", pathFormated);
	}

	@Test
	public void testPathForXmlFail() throws Exception {
		String pathFormated = Regex.formatInClassLoaderPath("org.eltonsolid.xml.class.properties.Domain.xmls");
		assertThat("org/eltonsolid/xml/class/properties/Domain.xmls", IsNot.not(pathFormated));
	}

	@Test
	public void testSql() throws Exception {
		String path = "file.sql";
		assertTrue(Regex.sql(path));
	}

	@Test
	public void testSqlCreate() throws Exception {
		String create = "domainCreate.sql";
		assertTrue(Regex.sqlCreate(create));
	}

	@Test
	public void testSqlCreateSmall() throws Exception {
		String create = "domaincreate.sql";
		assertTrue(Regex.sqlCreate(create));
	}

	@Test
	public void testSqlDrop() throws Exception {
		String drop = "domainDrop.sql";
		assertTrue(Regex.sqlDrop(drop));
	}

	@Test
	public void testSqlDropSmall() throws Exception {
		String drop = "domaindrop.sql";
		assertTrue(Regex.sqlDrop(drop));
	}

	@Test
	public void testReplaceDotForUnderscor() throws Exception {
		assertEquals("alias_address_value", Regex.replaceDotForUnderscor("alias.address.value"));
	}

	@Test
	public void testExtractJarFile() throws Exception {
		assertEquals("titan-snAPshot-01-0.jar", Regex.extractJarFile("rootPath/path/titan-snAPshot-01-0.jar"));
	}

	@Test
	public void testExtractJarFileComplex() throws Exception {
		assertEquals("titan-snAPshot-01-0.jar", Regex.extractJarFile("rootPath/path/titan-snAPshot-01-0.jar/META-INF/domain.xml"));
	}

	@Test(expected = NotAllowException.class)
	public void testExtractJarFileFail() throws Exception {
		Regex.extractJarFile("rootPath/path/titan-snAPshot-01-0/META-INF/domain.xml");
	}

	@Test
	public void testChangeTablesCreateForDrop() throws Exception {
		String tables = Regex.changeTablesCreateForDrop("create table group(name varchar2),create table user(name varchar2)");
		assertEquals("drop table group;drop table user;", tables);
	}

	@Test
	public void testChangeSequecesCreateForDrop() throws Exception {
		String tables = Regex
				.changeTablesCreateForDrop("create table group(name varchar2),create sequence seq_join_client process with 1,create table user (name varchar2)");
		assertEquals("drop table group;drop sequence seq_join_client;drop table user ;", tables);
	}

	@Test
	public void testChangeCreateConstraintForDrop() throws Exception {
		String drop = Regex.changeCreateConstraintForDrop("constraint subApp_pk primary key(subApp_id)");
		assertEquals("DROP constraint subApp_pk",drop);
	}
	
	@Test
	@Ignore
	public void testCapitalize() throws Exception {
		String result = Regex.capitalize("elton and solid");
		assertEquals("Eltonsolid", result);
	}
	
	
	
}
