package org.httprobot.data;

import org.httprobot.content.ContentTypeRef;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.document.InputLibrary;
import org.httprobot.data.field.TemplateFieldLibrary;

public class TemplateLibrary extends InputLibrary<ContentTypeRef> {

	TemplateFieldLibrary templateFieldLibrary;
	
	public TemplateFieldLibrary getTemplateFieldLibrary() {
		return templateFieldLibrary;
	}

	public TemplateLibrary() {
		super();
		templateFieldLibrary = new TemplateFieldLibrary();
	}
	
	@Override
	public InputDocument get(Object key) 
	{
		//Return a copy of the template
		return super.get(key).deepInputCopy();
	}
}
