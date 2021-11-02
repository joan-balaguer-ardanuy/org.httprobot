package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Unit;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Paginator extends Unit {

	/**
	 * -6116912845413725443L
	 */
	private static final long serialVersionUID = -6116912845413725443L;
	
	String anchorHrefAttributeValue;
	String anchorValue;
	Integer paginatorIncrement;
	String paginatorUrlPattern;
	
	@XmlElement
	public String getAnchorHrefAttributeValue() {
		return anchorHrefAttributeValue;
	}
	public void setAnchorHrefAttributeValue(String anchorHrefAttributeValue) {
		this.anchorHrefAttributeValue = anchorHrefAttributeValue;
	}

	@XmlElement
	public String getAnchorValue() {
		return anchorValue;
	}
	public void setAnchorValue(String anchorValue) {
		this.anchorValue = anchorValue;
	}

	@XmlElement
	public Integer getPaginatorIncrement() {
		return paginatorIncrement;
	}
	public void setPaginatorIncrement(Integer paginatorIncrement) {
		this.paginatorIncrement = paginatorIncrement;
	}

	@XmlElement
	public String getPaginatorUrlPattern() {
		return paginatorUrlPattern;
	}
	public void setPaginatorUrlPattern(String paginatorUrlPattern) {
		this.paginatorUrlPattern = paginatorUrlPattern;
	}

	public Paginator() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Paginator paginator = Paginator.class.cast(e.getSource());
		setAnchorHrefAttributeValue(paginator.getAnchorHrefAttributeValue());
		setAnchorValue(paginator.getAnchorValue());
		setPaginatorIncrement(paginator.getPaginatorIncrement());
		setPaginatorUrlPattern(paginator.getPaginatorUrlPattern());
	}
}
