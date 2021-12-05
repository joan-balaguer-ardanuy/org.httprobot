package org.httprobot.net;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.httprobot.configuration.Robot;

@WebService
@SOAPBinding(style=Style.RPC)
public interface MessageService {

	@WebMethod
	Robot getConfiguration();
}