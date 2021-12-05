package org.httprobot;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BrowserVersion", namespace = "http://org.httprobot")
@XmlEnum
public enum BrowserVersion
{
	CHROME,
	EDGE,
	FIREFOX,
	IE,
	OPERA,
	REMOTE
}
