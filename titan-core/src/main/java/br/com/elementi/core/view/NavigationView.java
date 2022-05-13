package br.com.elementi.core.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import br.com.elementi.core.constraint.Constraints;

import com.google.common.collect.Maps;

public class NavigationView implements SystemEventListener {

	private ActionEvent event;

	public NavigationView(ActionEvent event) {
		this.event = event;
	}

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof UIViewRoot;
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		System.out.println("NavigationView.processEvent()");
	}

	public static void navigate(ActionEvent event) throws Exception {
		new NavigationView(event).navigate();
	}

	@SuppressWarnings("deprecation")
	private void navigate() throws Exception {
		UIComponent source = event.getComponent();

		ActionSource actionSource = (ActionSource) source;
		FacesContext context = FacesContext.getCurrentInstance();

		Application application = context.getApplication();

		String outcome = (String) event.getComponent().getAttributes().get("outcome");
		Boolean keepView = event.getComponent().getAttributes().containsKey("keepView");

		MethodBinding binding = Constraints.notNull(actionSource.getAction());

		String invokeResult = (String) binding.invoke(context, null);
		Map<String, Object> from = new HashMap<String, Object>(context.getViewRoot().getViewMap());

		NavigationHandler navHandler = application.getNavigationHandler();
		navHandler.handleNavigation(context, binding.getExpressionString(), outcome == null ? invokeResult : outcome);

		context.renderResponse();

		if (keepView) {
			context.getViewRoot().getViewMap().putAll(from);
		}
	}

}
