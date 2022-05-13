package br.com.elementi.core.view;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.elementi.core.Unit;
import br.com.elementi.core.tools.I18n;
import br.com.elementi.core.tools.Reflect;
import br.com.elementi.test.DummyException;
import br.com.elementi.test.DummyPath;

import com.sun.faces.context.FacesContextImpl;

@Ignore
public class ShowTest extends Unit {

	private Show show;
	private FacesContext facesContext;

	@Before
	public void before() throws Exception {
		facesContext = spy(Reflect.forceInstance(FacesContextImpl.class));
		//show = Show.create(facesContext, I18n.create(DummyPath.class, new Locale("pt", "BR")));
	}

	@Test
	public void testInfo() throws Exception {
		show.info("");
		verify(facesContext).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
	}

	@Test
	public void testWarn() throws Exception {
		show.warn("");
		verify(facesContext).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
	}

	@Test
	public void testErro() throws Exception {
		show.erro("");
		verify(facesContext).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
	}

	@Test
	public void testException() throws Exception {

		verify(facesContext).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
	}

	@Test
	@Ignore
	public void testCheckException() throws Exception {
		//show.checkException(new DummyException());
		verify(facesContext).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));
	}

}
