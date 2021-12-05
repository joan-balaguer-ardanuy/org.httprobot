package org.httprobot;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DataType
{
	@XmlEnumValue(value = "BASE64")
	BASE64,
	@XmlEnumValue(value = "BOOLEAN")
	BOOLEAN,
	@XmlEnumValue(value = "DATETIME")
	DATETIME,
	@XmlEnumValue(value = "IMAGE")
	IMAGE,
	@XmlEnumValue(value = "LINK")
	LINK,
	@XmlEnumValue(value = "NUMBER")
	NUMBER,
	@XmlEnumValue(value = "TEXT")
	TEXT,
	@XmlEnumValue(value = "UUID")
	UUID,
	@XmlEnumValue(value = "XML")
	XML
}