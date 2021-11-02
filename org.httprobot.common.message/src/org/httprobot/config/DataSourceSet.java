package org.httprobot.config;

import java.util.Date;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class DataSourceSet extends AbstractConfig {

	/**
	 * 8929734311772147046L
	 */
	private static final long serialVersionUID = 8929734311772147046L;
	
	Date dateCreated;	
	LinkedHashSet<DataSourceRef> dataSourceRef;
	
	@XmlAttribute
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@XmlElement
	public LinkedHashSet<DataSourceRef> getDataSourceRef() {
		return dataSourceRef;
	}
	public void setDataSourceRef(LinkedHashSet<DataSourceRef> dataSourceRef) {
		this.dataSourceRef = dataSourceRef;
	}

	public DataSourceSet() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		DataSourceSet dataSourceList = DataSourceSet.class.cast(e.getSource());
		setDateCreated(dataSourceList.getDateCreated());
		setDataSourceRef(dataSourceList.getDataSourceRef());
	}
}
