package org.httprobot;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum NextPageMethod
{
	@XmlEnumValue(value = "CLICK_NEXT_ELEMENT")
	CLICK_NEXT_ELEMENT,
	@XmlEnumValue(value = "INCREMENTAL_HTTP_GET")
	INCREMENTAL_HTTP_GET
}
