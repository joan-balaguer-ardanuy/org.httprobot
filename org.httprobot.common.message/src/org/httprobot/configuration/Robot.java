package org.httprobot.configuration;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfiguration;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.datatype.DataSource;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Robot extends AbstractConfiguration {

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
	public Robot() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Robot configuration = Robot.class.cast(e.getSource());
		setContentTypeRoot(configuration.getContentTypeRoot());
		setDataSource(configuration.getDataSource());
	}
}
