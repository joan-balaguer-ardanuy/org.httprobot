package org.httprobot.data.field;

import org.apache.solr.common.SolrInputField;
import org.httprobot.content.FieldRef;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class InputField extends SolrInputField {

	/**
	 * -7323581309108317714L
	 */
	private static final long serialVersionUID = -7323581309108317714L;
	
	FieldRef fieldRef;
	Boolean isParsed;
	Boolean isDomNode;
	
	public FieldRef getFieldRef() {
		return fieldRef;
	}

	public void setFieldRef(FieldRef fieldRef) {
		this.fieldRef = fieldRef;
	}

	public Boolean getIsParsed() {
		return isParsed;
	}

	public void setIsParsed(Boolean isParsed) {
		this.isParsed = isParsed;
	}

	public Boolean getIsDomNode() {
		return isDomNode;
	}

	public void setIsDomNode(Boolean isDomNode) {
		this.isDomNode = isDomNode;
	}
	
	public InputField(FieldRef fieldRef)
	{
		super(fieldRef.getName());
		
		this.fieldRef = fieldRef;
	}
	public InputField(FieldRef fieldRef, SolrInputField solrField)
	{
		super(fieldRef.getName());
		
		this.fieldRef = fieldRef;
		this.setValue(solrField.getValue());
	}
	public InputField deepInputCopy()
	{
		InputField clone = new InputField(fieldRef, deepCopy());
		
		return clone;
	}

	public void setValue(Object v) {

		if (v instanceof DomNode) {
			this.setIsDomNode(true);
			setIsParsed(false);
		} else {
			this.setIsDomNode(false);

			switch (getFieldRef().getDataType()) {
			case BOOLEAN:
				if (Boolean.class.cast(v) != null) {
					setIsParsed(true);
				}
				break;

			case TEXT:
				if (String.class.cast(v) != null) {
					setIsParsed(true);
				}
				// TODO Anything else...?
				break;

			default:
				break;
			}
		}
		// Call super method
		super.setValue(v);
	}
}