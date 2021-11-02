package org.httprobot.unit.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.Enums.UnitBrowserVersion;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class BrowserVersionAdapter extends XmlAdapter<UnitBrowserVersion, BrowserVersion>{

	public BrowserVersionAdapter() {
	}

	@Override
	public BrowserVersion unmarshal(UnitBrowserVersion v) throws Exception {
		switch (v) {
		case CHROME:
			return BrowserVersion.CHROME;
		case FIREFOX:
			return BrowserVersion.FIREFOX;
		case IE:
			return BrowserVersion.INTERNET_EXPLORER;
		case EDGE:
			return BrowserVersion.EDGE;
		default:
			return BrowserVersion.getDefault();
		}
	}

	@Override
	public UnitBrowserVersion marshal(BrowserVersion v) throws Exception {
		if (v == BrowserVersion.CHROME) {
			return UnitBrowserVersion.CHROME;
		}
		else if(v == BrowserVersion.FIREFOX) {
			return UnitBrowserVersion.FIREFOX;
		}
		else if(v == BrowserVersion.INTERNET_EXPLORER) {
			return UnitBrowserVersion.IE;
		}
		else if(v == BrowserVersion.EDGE) {
			return UnitBrowserVersion.EDGE;
		}
		else {
			// best supported
			return UnitBrowserVersion.CHROME;
		}
	}
}