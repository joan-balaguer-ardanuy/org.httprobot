package org.httprobot;

import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.EventArgs;
import org.openqa.selenium.WebDriver;

public abstract class AbstractParent
	extends AbstractListener<Parent> 
		implements Parent {

	/**
	 * -4305679696716005954L
	 */
	private static final long serialVersionUID = -4305679696716005954L;

	Control control;

	ContentTypeRoot contentTypeRoot;
	DocumentLibrary documentLibrary;
	TemplateLibrary templateLibrary;
	
	Map<String, String> parameters;
	
	public Control getControl() {
		return control;
	}
	
	@XmlTransient
	public ContentTypeRoot getContentTypeRoot() {
		if (contentTypeRoot != null) {
			return contentTypeRoot;
		} else if (parent != null) {
			return getParent().getContentTypeRoot();
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
			return getParent().getDocumentLibrary();
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
			return getParent().getTemplateLibrary();
		} else return null;
	}
	public void setTemplateLibrary(TemplateLibrary templateLibrary) {
		this.templateLibrary = templateLibrary;
	}
	@XmlTransient
	public Map<String, String> getConstants() {
		if (parameters != null) {
			return parameters;
		} else if (parent != null) {
			return getParent().getConstants();
		} else return null;
	}
	public void setConstants(Map<String, String> constants) {
		this.parameters = constants;
	}
	@XmlTransient
	public WebDriver getWebDriver() {
		if (parent != null) {
			return getParent().getWebDriver();
		} else return null;
	}
	public void setWebDriver(WebDriver webDriver) {
		if(parent != null) {
			getParent().setWebDriver(webDriver);
		}
	}
	
	public AbstractParent() {
		super();
	}
	public AbstractParent(Message message, Class<? extends Control> type, Parent parent) {
		super(message.getName());

		control = instance(type, message);
	}
	@Override
	public void start() {
		control.load();
	}
	@Override
	public void stop() {
		
	}

	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			break;
		case CONTROL_LOADED:
			if (hasChildren()) {
				// Start each child manager
				while (hasNext()) {
					Parent manager = next();
					manager.start();
				}
				reset();
			}
			SendEvent(new EventArgs(this, EventType.PARENT_STARTED));
			break;

		default:
			break;
		}
	}
}