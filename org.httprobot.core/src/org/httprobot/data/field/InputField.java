package org.httprobot.data.field;

import org.apache.solr.common.SolrInputField;
import org.httprobot.content.FieldRef;
import org.openqa.selenium.WebElement;

public class InputField extends SolrInputField {

	/**
	 * -7323581309108317714L
	 */
	private static final long serialVersionUID = -7323581309108317714L;
	
	FieldRef fieldRef;
	Boolean isParsed;
	Boolean isWebElement;
	
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

	public Boolean getIsWebElement() {
		return isWebElement;
	}

	public void setIsWebElement(Boolean isWebElement) {
		this.isWebElement = isWebElement;
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

		if (v instanceof WebElement) {
			this.setIsWebElement(true);
			setIsParsed(false);
		} else {
			this.setIsWebElement(false);

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