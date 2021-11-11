package org.httprobot.data;

import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.FieldRef;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.document.InputDocumentLibrary;
import org.httprobot.data.field.FieldLibrary;
import org.openqa.selenium.WebElement;

public class DocumentLibrary extends InputDocumentLibrary<WebElement, FieldRef> {
	
	ContentTypeRef contentTypeRef;

	public ContentTypeRef getContentTypeRef()
	{
		return this.contentTypeRef;
	}
	
	public DocumentLibrary(ContentTypeRef contentTypeRef, InputDocument templateDocument, FieldLibrary<FieldRef> templateFields) {
		
		super(templateDocument, templateFields);
		
		//Set inherited data
		this.contentTypeRef = contentTypeRef;
	}
	@Override
	public InputDocument put(WebElement key, InputDocument value) {
		
		if(value.getContentType().getUuid().equals(this.contentTypeRef.getUuid()))
		{	
			//Store document
			super.put(key, value);
			
			return value;
		}
		else
		{
			return null;
		}
	}
}