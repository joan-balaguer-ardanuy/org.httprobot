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

/**
 * XML message. Inherits {@link AbstractCommandListener}.
 * It is {@link Message}. All objects of the framework will inherit
 * this class except the event arguments.
 * 
 * @author joan
 *
 */
public abstract class XML 
	extends AbstractCommandListener 
		implements Message {
	
	/**
	 * 5362492597715736613L
	 */
	private static final long serialVersionUID = 5362492597715736613L;

	/**
	 * The universal unique ID
	 */
	private UUID uuid;
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
	/**
	 * The {@link Set} of {@link Message}.
	 */
	Set<Message> xmlListeners;
	
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
	
	/**
	 * {@link XML} message default class constructor.
	 */
	public XML() {
		xmlListeners = new LinkedHashSet<Message>();
		addMessageListener(this);
	}
	/**
	 * {@link XML} message class constructor.
	 * @param uuid {@link UUID} the universal unique ID.
	 */
	public XML(UUID uuid) {
		this();
		this.uuid = uuid;
	}
	
	@Override
	public void OnMessageMarshalled(MessageEventArgs e) {
		
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		// Cast event source.
		Message xml = Message.class.cast(e.getSource());
		// Set read UUID
		setUuid(xml.getUuid());
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		CommandListenerEvent(e);
	}
	
	/**
	 * Adds new {@link Message} to message listeners {@link Set}.
	 * @param listener
	 */
	public final void addMessageListener(Message listener) {
		xmlListeners.add(listener);
	}
	/**
	 * Removes old {@link Message} from message listeners {@link Set}.
	 * @param listener
	 */
	public final void removeMessageListener(Message listener) {
		xmlListeners.remove(listener);
	}
	/**
	 * Fires XML event
	 * @param e {@link MessageEventArgs} the arguments of the event.
	 */
	protected final void XmlEvent(MessageEventArgs e)
	{
		// For each MessageListeener in XML listeners set
		for (Message listener : xmlListeners) 
		{
			// comute XML event type
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
	/**
	 * XML unmarshall method. Generates new {@link JAXBContext} for current class,
	 * instances new {@link Unmarshaller} object and unmarshalls the {@link InputStream} argument.
	 * @param inputStream {@link InputStream} the input stream to be unmarshalled.
	 * @throws JAXBException thrown when something is wrong
	 */
	public final void unmarshal(InputStream inputStream) throws JAXBException {
		try {
			jaxbContext = JAXBContext.newInstance(this.getClass());
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Message obj = Message.class.cast(jaxbUnmarshaller.unmarshal(inputStream));
			XmlEvent(new MessageEventArgs(obj, XmlEventType.MESSAGE_UNMARSHALLED));
		} catch (Exception e) {
			throw new JAXBException(e.getMessage(), e.getCause());
		}
	}
	/**
	 * XML marshall method. Generates new {@link JAXBContext} for current class,
	 * instances new {@link Marshaller} object and marshalls the {@link OutputStream} argument.
	 * @param outputStrem {@link OutputStream} the output stream to be marshalled.
	 * @throws JAXBException when something is wrong
	 */
	public final void marshal(OutputStream outputStrem) throws JAXBException {
		try {
			jaxbContext = JAXBContext.newInstance(this.getClass());
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this, outputStrem);
			XmlEvent(new MessageEventArgs(this, XmlEventType.MESSAGE_MARSHALLED));
		} catch (JAXBException e) {
			throw new JAXBException(e.getMessage(), e.getCause());
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		try 
		{
			// instances new JAXBContext for current class
			jaxbContext = JAXBContext.newInstance(this.getClass());
			// create marshaller
			jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// instances new StringWriter
			StringWriter sw = new StringWriter();
			// marshall XML message into StringWriter
			jaxbMarshaller.marshal(this, sw);
			// get the XML message as string
			String strXml = sw.toString();
			// return string XML message
			return strXml;
		} 
		catch (JAXBException e) {
			// is something is wrong print stack trace
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Writes the string XML message into a file.
	 * @param path {@link String} the destination path of the string XML message.
	 */
	public void toFile(String path) {
		// parse XML message into string.
		String str = toString();
		// declare new buffered writer
	    BufferedWriter writer;
		try {
			// instances buffered writer with new file writer with destination path as argument
			writer = new BufferedWriter(new FileWriter(path));
			// write the string to the file
		    writer.write(str);
		    // close writer
		    writer.close();
		} catch (IOException e) {
			// if something is wrong print stack trace
			e.printStackTrace();
		}
	}
}