package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractDataType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.parameter.ServerUrl;
import org.httprobot.parameter.StartUrl;
import org.httprobot.unit.Action;

@XmlRootElement
public final class DataSource extends AbstractDataType {

	/**
	 * 5329113787974867972L
	 */
	private static final long serialVersionUID = 5329113787974867972L;

	Action action;
	ContentTypeRef contentTypeRef;
	DocumentRoot documentRoot;
	String sourceName;
	ServerUrl serverUrl;
	StartUrl startUrl;
	
	@XmlElement
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	@XmlElement
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	
	@XmlElement
	public DocumentRoot getDocumentRoot() {
		return documentRoot;
	}
	public void setDocumentRoot(DocumentRoot documentRoot) {
		this.documentRoot = documentRoot;
	}
	
	@XmlElement
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	@XmlElement
	public ServerUrl getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(ServerUrl serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	@XmlElement
	public StartUrl getStartUrl() {
		return startUrl;
	}
	public void setStartUrl(StartUrl startUrl) {
		this.startUrl = startUrl;
	}

	public DataSource() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		DataSource dataSource = DataSource.class.cast(e.getSource());
		setAction(dataSource.getAction());
		setContentTypeRef(dataSource.getContentTypeRef());
		setDocumentRoot(dataSource.getDocumentRoot());
		setSourceName(dataSource.getSourceName());
		setServerUrl(dataSource.getServerUrl());
		setStartUrl(dataSource.getStartUrl());
	}
}