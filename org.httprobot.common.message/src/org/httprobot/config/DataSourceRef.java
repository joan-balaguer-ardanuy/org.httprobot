package org.httprobot.config;

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Config;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class DataSourceRef extends Config {

	/**
	 * 2652815586777993713L
	 */
	private static final long serialVersionUID = 2652815586777993713L;

	UUID serverInfoID;
	String serverInfoName;
	Integer priority;
	Date lastUpdate;
	
	@XmlElement
	public UUID getServerInfoID() {
		return serverInfoID;
	}
	public void setServerInfoID(UUID serverInfoID) {
		this.serverInfoID = serverInfoID;
	}
	
	@XmlElement
	public String getServerInfoName() {
		return serverInfoName;
	}
	public void setServerInfoName(String serverInfoName) {
		this.serverInfoName = serverInfoName;
	}
	
	@XmlElement
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@XmlElement
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public DataSourceRef() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		DataSourceRef dataSourceRef = DataSourceRef.class.cast(e.getSource());
		setServerInfoID(dataSourceRef.getServerInfoID());
		setServerInfoName(dataSourceRef.getServerInfoName());
		setPriority(dataSourceRef.getPriority());
		setLastUpdate(dataSourceRef.getLastUpdate());
	}
}