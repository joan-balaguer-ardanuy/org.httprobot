package org.httprobot;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BrowserVersion")
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
