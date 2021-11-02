package org.httprobot;

import java.util.LinkedHashSet;
import java.util.Set;

import org.httprobot.event.CommandEventArgs;


public abstract class CLI implements CommandListener {

	/**
	 * 1182412397820363386L
	 */
	private static final long serialVersionUID = 1182412397820363386L;

	String path;
	
	Set<CommandListener> commandListeners;
	
	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
	
	public CLI() {
		this.commandListeners = new LinkedHashSet<CommandListener>();
	}

	public final void addCommandListener(CommandListener listener) {
		commandListeners.add(listener);
	}
	public final void removeCommandListener(CommandListener listener) {
		commandListeners.remove(listener);
	}
	protected void CommandLineEvent(CommandEventArgs e) {
		for(CommandListener listener : commandListeners) {
			listener.OnCommandReceived(e);
		}
	}
	@Override
	public abstract void OnCommandReceived(CommandEventArgs e);
	
	protected static <X> X instance(Class<X> type, Object... args) {
		try {
			return type.getDeclaredConstructor(getClasses(args)).newInstance(args);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	static Class<?>[] getClasses(Object... objects) {
		Class<?>[] types = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}
		return types;
	}
}
