package org.httprobot;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum FinalCondition
{
	@XmlEnumValue(value = "MISSING_ELEMENT")
	MISSING_ELEMENT,
	@XmlEnumValue(value = "END_MESSAGE")
	END_MESSAGE
}
