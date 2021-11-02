package org.httprobot;

import java.util.Date;

public abstract class AbstractDataType extends XML {

	/**
	 * -2808293996272045037L
	 */
	private static final long serialVersionUID = -2808293996272045037L;

	Date NTP;
	
	public Date getNTP() {
		return NTP;
	}
	public void setNTP(Date nTP) {
		NTP = nTP;
	}

	public AbstractDataType() {
		super();
	}
}
