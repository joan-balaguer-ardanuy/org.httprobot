package org.httprobot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

public abstract class Parent<T extends Control<?>> 
	extends XML 
		implements ControlListener, ParentListener, 
			Iterator<ParentListener> {

	/**
	 * -4305679696716005954L
	 */
	private static final long serialVersionUID = -4305679696716005954L;

	T control;

	Set<ParentListener> managerListeners;
	
	int currentManagerIndex;
	List<ParentListener> childManagers;

	ParentListener parent;
	
	ContentTypeRoot contentTypeRoot;
	DocumentLibrary documentLibrary;
	TemplateLibrary templateLibrary;
	
	Map<String, String> parameterConstants;
	
	@XmlTransient
	public ParentListener getParent() {
		return parent;
	}
	public void setParent(ParentListener parent) {
		this.parent = parent;
	}
	public T getControl() {
		return control;
	}
	public void setControl(T control) {
		this.control = control;
	}
	@XmlTransient
	public ContentTypeRoot getContentTypeRoot() {
		if (contentTypeRoot != null) {
			return contentTypeRoot;
		} else if (parent != null) {
			return parent.getContentTypeRoot();
		} else return null;
	}
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		this.contentTypeRoot = contentTypeRoot;
	}
	@XmlTransient
	public DocumentLibrary getDocumentLibrary() {
		if (documentLibrary != null) {
			return documentLibrary;
		} else if (parent != null) {
			return parent.getDocumentLibrary();
		} else return null;
	}
	public void setDocumentLibrary(DocumentLibrary documentLibrary) {
		this.documentLibrary = documentLibrary;
	}
	@XmlTransient
	public TemplateLibrary getTemplateLibrary() {
		if(templateLibrary != null) {
			return templateLibrary;
		} else if(parent != null) {
			return parent.getTemplateLibrary();
		} else return null;
	}
	public void setTemplateLibrary(TemplateLibrary templateLibrary) {
		this.templateLibrary = templateLibrary;
	}
	@XmlTransient
	public Map<String, String> getConstants() {
		if (parameterConstants != null) {
			return parameterConstants;
		} else if (parent != null) {
			return parent.getConstants();
		} else return null;
	}
	public void setConstants(Map<String, String> constants) {
		this.parameterConstants = constants;
	}
	@XmlTransient
	public WebDriver getWebDriver() {
		if (parent != null) {
			return parent.getWebDriver();
		} else return null;
	}
	public void setWebDriver(WebDriver webDriver) {
		if(parent != null) {
			parent.setWebDriver(webDriver);
		}
	}
	
	public Parent() {
		super(UUID.randomUUID());
		
		managerListeners = new LinkedHashSet<ParentListener>();
		
		currentManagerIndex = 0;
		childManagers = new ArrayList<ParentListener>();
		
		addManagerListener(this);	
	}
	public Parent(XML message, Class<T> type, ParentListener parent) {
		super(message.getUuid());

		control = instance(type, message, this);
		
		managerListeners = new LinkedHashSet<ParentListener>();
		
		this.parent = parent;
		
		currentManagerIndex = 0;
		childManagers = new ArrayList<ParentListener>();
		
		addManagerListener(this);
		addManagerListener(parent);
	}

	public void addManagerListener(ParentListener listener) {
		managerListeners.add(listener);
	}
	public void removeManagerListener(ParentListener listener) {
		managerListeners.remove(listener);
	}
	protected void ManagerEvent(ManagerEventArgs e) {
		for(ParentListener listener : managerListeners) {
			listener.OnParentEvent(e);
		}
	}
	public void addChildManager(ParentListener manager) {
		childManagers.add(manager);
	}
	public void reset() {
		currentManagerIndex = 0;
	}
	public boolean hasChildManagers() {
		boolean isEmpty = (childManagers != null ? childManagers.isEmpty() : true);
		return !isEmpty;
	}
	@Override
	public boolean hasNext() {
		return currentManagerIndex < childManagers.size();
	}
	@Override
	public ParentListener next() {
		ParentListener listener = childManagers.get(currentManagerIndex);
		currentManagerIndex++;
		return listener;
	}
	@Override
	public void start() {
		control.loadControl();
	}
	@Override
	public void stop() {
		
	}
	@Override
	public abstract void OnParentEvent(ManagerEventArgs e);

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (hasChildManagers()) {
			// Start each child manager
			while (hasNext()) {
				ParentListener manager = next();
				manager.start();
			}
			reset();
		}
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
	}
}