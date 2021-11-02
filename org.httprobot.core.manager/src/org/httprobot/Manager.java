package org.httprobot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.Enums.ManagerEventType;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.event.ManagerEventArgs;

public abstract class Manager<T extends Control<?>> 
	extends XML 
		implements ControlListener, ManagerListener, 
			Iterator<ManagerListener> {

	/**
	 * -4305679696716005954L
	 */
	private static final long serialVersionUID = -4305679696716005954L;

	T control;

	Set<ManagerListener> managerListeners;
	
	int currentManagerIndex;
	List<ManagerListener> childManagers;

	Manager<?> parent;
	
	ContentTypeRoot contentTypeRoot;
	DocumentLibrary documentLibrary;
	TemplateLibrary templateLibrary;
	
	Map<String, String> parameterBannedWords;
	Map<String, String> parameterConstants;

	@XmlTransient
	public Manager<?> getParent() {
		return parent;
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
	public Map<String,String> getBannedWords() {
		if (parameterBannedWords != null) {
			return parameterBannedWords;
		} else if (parent != null) {
			return parent.getBannedWords();
		} else return null;
	}
	public void setBannedWords(Map<String, String> bannedWords) {
		this.parameterBannedWords = bannedWords;
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
	
	public Manager() {

	}
	public Manager(XML message, Class<T> type, Manager<?> parent) {
		super(message.getUuid());

		control = instance(type, message, this);
		
		managerListeners = new LinkedHashSet<ManagerListener>();
		
		this.parent = parent;
		
		currentManagerIndex = 0;
		childManagers = new ArrayList<ManagerListener>();
		
		addManagerListener(this);
		addManagerListener(parent);
	}

	public void addManagerListener(ManagerListener listener) {
		managerListeners.add(listener);
	}
	public void removeManagerListener(ManagerListener listener) {
		managerListeners.remove(listener);
	}
	protected void ManagerEvent(ManagerEventArgs e) {
		for(ManagerListener listener : managerListeners) {
			listener.OnManagerEvent(e);
		}
	}
	
	public void addChildManager(ManagerListener manager) {
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
	public ManagerListener next() {
		ManagerListener listener = childManagers.get(currentManagerIndex);
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
	public abstract void OnManagerEvent(ManagerEventArgs e);

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		
	}

	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (hasChildManagers()) {
			// Start each child manager
			while (hasNext()) {
				ManagerListener manager = next();
				manager.start();
			}
			reset();
		}
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
	}
}