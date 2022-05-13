package br.com.elementi.core.view;

import java.io.IOException;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.sun.faces.application.view.FaceletViewHandlingStrategy;

public class ViewHandler extends FaceletViewHandlingStrategy {

	@Override
	public void buildView(FacesContext ctx, UIViewRoot view) throws IOException {
		super.buildView(ctx, view);
	}

}
