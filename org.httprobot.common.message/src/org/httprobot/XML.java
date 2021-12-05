package org.httprobot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;

import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.MessageEventArgs;

public abstract class XML 
	extends CLI 
		implements MessageListener {
	
	/**
	 * 5362492597715736613L
	 */
	private static final long serialVersionUID = 5362492597715736613L;

	UUID uuid;
	
	/**
	 * JAXB context
	 */
	private JAXBContext jaxbContext;
	/**
	 * The marshaller
	 */
	private Marshaller jaxbMarshaller;
	/**
	 * The unmarshaller
	 */
	private Unmarshaller jaxbUnmarshaller;
	
	@Override
	@XmlAttribute
	public UUID getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(UUID uuid) {
		if(uuid != null) {
			this.uuid  = uuid;
		}
		else {
			this.uuid = UUID.randomUUID();
		}
	}
	
	Set<MessageListener> xmlListeners;
	
	public XML() {
		xmlListeners = new LinkedHashSet<MessageListener>();
		addMessageListener(this);
	}
	public XML(UUID uuid) {
		this();
		this.uuid = uuid;
	}
	
	@Override
	public void OnMessageMarshalled(MessageEventArgs e) {
		
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		MessageListener xml = MessageListener.class.cast(e.getSource());
		setUuid(xml.getUuid());
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		CommandLineEvent(e);
	}
	
	public final void addMessageListener(MessageListener listener) {
		xmlListeners.add(listener);
	}
	public final void removeMessageListener(MessageListener listener) {
		xmlListeners.remove(listener);
	}
	protected final void XmlEvent(MessageEventArgs e)
	{
		for (MessageListener listener : xmlListeners) 
		{
			switch (e.getXmlEventType()) 
			{
				case MESSAGE_MARSHALLED:
					listener.OnMessageMarshalled(e);
					break;
				case MESSAGE_UNMARSHALLED:
					listener.OnMessageUnmarshalled(e);
					break;
				default:
					break;
			}
		}
	}
	public final void unmarshal(InputStream inputStream) throws JAXBException
	{
		if(getPath() != null)
		{
			try 
			{
				jaxbContext = JAXBContext.newInstance(this.getClass());
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				MessageListener obj = MessageListener.class.cast(jaxbUnmarshaller.unmarshal(inputStream));
				XmlEvent(new MessageEventArgs(obj, XmlEventType.MESSAGE_UNMARSHALLED));
			} 
			catch (Exception e) 
			{
				throw new JAXBException(e.getMessage(), e.getCause());
			}
		}
		else
		{
			throw new JAXBException(this.getClass().toString() + ".unmarshal(ObjectInputStream): Destination path is null");
		}
	}
	public final void marshal(OutputStream outputStrem) throws JAXBException
	{
		if (getPath() != null) 
		{
			try 
			{
				jaxbContext = JAXBContext.newInstance(this.getClass());
				jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
				jaxbMarshaller.marshal(this, outputStrem);
				XmlEvent(new MessageEventArgs(this, XmlEventType.MESSAGE_MARSHALLED));
			}
			catch (JAXBException e) 
			{
				throw new JAXBException(e.getMessage(), e.getCause());
			}
		}
		else
		{
			throw new JAXBException(this.getClass().toString() + ".marshal(OutputStream): Destination path is null");
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public java.lang.String toString() 
	{
		try 
		{
			jaxbContext = JAXBContext.newInstance(this.getClass());
			jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(this, sw);	
			java.lang.String strXml = sw.toString();
			
			return strXml;
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	public void toFile(java.lang.String path) {
		java.lang.String str = toString();
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path));
		    writer.write(str);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
