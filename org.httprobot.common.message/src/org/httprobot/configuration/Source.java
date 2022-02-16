package org.httprobot.configuration;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfiguration;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.datatype.DataSource;
import org.httprobot.event.MessageEventArgs;

/**
 * The source XML message of the org.httprobot.
 * The parent instance will execute the orders set in this XML message.
 * It inherits {@link AbstractConfiguration} class and encapsulates
 * a {@link ContentTypeRoot}, that is the definition of the database for all data sources,
 * and {@link LinkedHashSet} of {@link DataSource} the contains all data to get information
 * from http servers.
 * @author joan
 *
 */
@XmlRootElement
public final class Source 
	extends AbstractConfiguration {

	/**
	 * 8762442432069232238L
	 */
	private static final long serialVersionUID = 8762442432069232238L;

	/**
	 * The root content type.
	 */
	ContentTypeRoot contentTypeRoot;
	/**
	 * The set of data sources.
	 */
	LinkedHashSet<DataSource> dataSource;
	
	/**
	 * Returns the {@link ContentTypeRoot}.
	 * @return {@link ContentTypeRoot} the root content type.
	 */
	@XmlElement
	public ContentTypeRoot getContentTypeRoot() {
		return contentTypeRoot;
	}
	/**
	 * Sets the {@link ContentTypeRoot}.
	 * @param contentTypeRoot {@link ContentTypeRoot} the root content type.
	 */
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		this.contentTypeRoot = contentTypeRoot;
	}
	/**
	 * Returns a {@link LinkedHashSet} of {@link DataSource}.
	 * @return the {@link LinkedHashSet} of {@link DataSource}.
	 */
	@XmlElement
	public LinkedHashSet<DataSource> getDataSource() {
		return dataSource;
	}
	/**
	 * Sets a {@link LinkedHashSet} of {@link DataSource}
	 * @param dataSource the {@link LinkedHashSet} of {@link DataSource}.
	 */
	public void setDataSource(LinkedHashSet<DataSource> dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * {@link Source} default class constructor.
	 */
	public Source() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		Source source = Source.class.cast(e.getSource());
		// Set properties
		setContentTypeRoot(source.getContentTypeRoot());
		setDataSource(source.getDataSource());
	}
}
