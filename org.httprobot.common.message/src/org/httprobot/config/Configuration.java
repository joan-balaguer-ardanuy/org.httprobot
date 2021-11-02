package org.httprobot.config;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Config;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.datatype.DataSource;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Configuration extends Config {

	/**
	 * 8762442432069232238L
	 */
	private static final long serialVersionUID = 8762442432069232238L;

	ContentTypeRoot contentTypeRoot;
	LinkedHashSet<DataSource> dataSource;
	
	@XmlElement
	public ContentTypeRoot getContentTypeRoot() {
		return contentTypeRoot;
	}
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		this.contentTypeRoot = contentTypeRoot;
	}
	@XmlElement
	public LinkedHashSet<DataSource> getDataSource() {
		return dataSource;
	}
	public void setDataSource(LinkedHashSet<DataSource> dataSource) {
		this.dataSource = dataSource;
	}
	public Configuration() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Configuration configuration = Configuration.class.cast(e.getSource());
		setContentTypeRoot(configuration.getContentTypeRoot());
		setDataSource(configuration.getDataSource());
	}
}
