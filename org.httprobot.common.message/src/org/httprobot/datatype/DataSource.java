package org.httprobot.datatype;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractDataType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.parameter.Constant;
import org.httprobot.unit.Action;

/**
 * Data source class. Inherits {@link AbstractDataType}.
 * It encapsulates {@link Action} object, that is the first action
 * during the process of getting data; a {@link ContentTypeRef} that's
 * the reference of the collecting content; the {@link DocumentRoot} is
 * the first step of the process; the name of the data source and a
 * {@link LinkedHashSet} of {@link Constant} that collects the parameters
 * used by the action object.
 * @author joan
 *
 */
@XmlRootElement
public final class DataSource extends AbstractDataType {

	/**
	 * 5329113787974867972L
	 */
	private static final long serialVersionUID = 5329113787974867972L;

	/**
	 * The firts action to run on this data source
	 */
	Action action;
	/**
	 * The content type reference mapped to this data source
	 */
	ContentTypeRef contentTypeRef;
	/**
	 * The main step to get data from this data source.
	 */
	DocumentRoot documentRoot;
	/**
	 * The data source name.
	 */
	String sourceName;
	/**
	 * The global parameters used by actions in whole data source.
	 */
	LinkedHashSet<Constant> constant;
	
	/**
	 * Returns the {@link Action} mapped to this data source.
	 * @return the {@link Action} mapped to this data source.
	 */
	@XmlElement
	public Action getAction() {
		return action;
	}
	/**
	 * Sets the {@link Action} mapped to this data source.
	 * @param action the {@link Action} mapped to this data source.
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * Returns the {@link ContentTypeRef} mapped to this data source.
	 * @return the {@link ContentTypeRef} mapped to this data source.
	 */
	@XmlElement
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
	/**
	 * Sets the {@link ContentTypeRef} mapped to this data source.
	 * @param contentTypeRef the {@link ContentTypeRef} mapped to this data source.
	 */
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	
	/**
	 * Returns the {@link DocumentRoot} mapped to this data source.
	 * @return the {@link DocumentRoot} mapped to this data source.
	 */
	@XmlElement
	public DocumentRoot getDocumentRoot() {
		return documentRoot;
	}
	/**
	 * Sets the {@link DocumentRoot} mapped to this data source.
	 * @param documentRoot the {@link DocumentRoot} mapped to this data source.
	 */
	public void setDocumentRoot(DocumentRoot documentRoot) {
		this.documentRoot = documentRoot;
	}
	
	/**
	 * Returns the source name of this data source.
	 * @return the source name of this data source.
	 */
	@XmlElement
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * Sets the source name of this data source.
	 * @param sourceName the source name of this data source.
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * Returns {@link LinkedHashSet} of {@link Constant}.
	 * These constants are global and each child object will be able to get them.
	 * @return
	 */
	@XmlElement
	public LinkedHashSet<Constant> getConstant() {
		return constant;
	}
	/**
	 * Sets a {@link LinkedHashSet} of {@link Constant}.
	 * @param constant the {@link LinkedHashSet} of {@link Constant}.
	 */
	public void setConstant(LinkedHashSet<Constant> constant) {
		this.constant = constant;
	}

	/**
	 * {@link DataSource} default class constructor.
	 */
	public DataSource() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		DataSource dataSource = DataSource.class.cast(e.getSource());
		// Set properties
		setAction(dataSource.getAction());
		setContentTypeRef(dataSource.getContentTypeRef());
		setDocumentRoot(dataSource.getDocumentRoot());
		setSourceName(dataSource.getSourceName());
		setConstant(dataSource.getConstant());
	}
}