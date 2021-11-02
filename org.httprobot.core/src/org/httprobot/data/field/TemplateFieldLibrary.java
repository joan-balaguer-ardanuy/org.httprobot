package org.httprobot.data.field;

import org.httprobot.content.FieldRef;

public class TemplateFieldLibrary extends FieldLibrary<FieldRef> {

	public TemplateFieldLibrary() {
		super();
	}
	@Override
	public InputField get(Object key) 
	{
		return super.get(key).deepInputCopy();
	}
}
