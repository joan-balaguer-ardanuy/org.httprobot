package org.httprobot.datatype;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractDataType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.parameter.Constant;
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
	LinkedHashSet<Constant> constant;
	
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
	public LinkedHashSet<Constant> getConstant() {
		return constant;
	}
	public void setConstant(LinkedHashSet<Constant> constant) {
		this.constant = constant;
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
		setConstant(dataSource.getConstant());
	}
}