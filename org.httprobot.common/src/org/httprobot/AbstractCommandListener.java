package org.httprobot;

import java.util.LinkedHashSet;
import java.util.Set;

import org.httprobot.event.CommandEventArgs;


/**
 * Abstract command listener class.
 * It's the implementation of {@link CommandListener} interface.
 * All instances of the framework will inherit this class.
 * 
 * @author joan
 *
 */
public abstract class AbstractCommandListener implements CommandListener {

	/**
	 * 1182412397820363386L
	 */
	private static final long serialVersionUID = 1182412397820363386L;
	
	/**
	 * The command listenrs set.
	 */
	Set<CommandListener> commandListeners;
	
	/**
	 * {@link AbstractCommandListener} default class constructor.
	 */
	public AbstractCommandListener() {
		this.commandListeners = new LinkedHashSet<CommandListener>();
	}

	/**
	 * Adds new command listener to the command listeners set.
	 * @param listener {@link CommandListener} the new listener to be added.
	 */
	public final void addCommandListener(CommandListener listener) {
		commandListeners.add(listener);
	}
	/**
	 * Removes old command listener from the command listenrs set.
	 * @param listener {@link CommandListener} the old listener to be removed.
	 */
	public final void removeCommandListener(CommandListener listener) {
		commandListeners.remove(listener);
	}
	/**
	 * Fires {@link CommandListener} event to all command listeners added in the set.
	 * @param e {@link CommandEventArgs} the arguments of the event
	 */
	protected void CommandListenerEvent(CommandEventArgs e) {
		for(CommandListener listener : commandListeners) {
			listener.OnCommandReceived(e);
		}
	}
	@Override
	public abstract void OnCommandReceived(CommandEventArgs e);
	
	/**
	 * Intances new object.
	 * @param <X> the parameter type of the returned object
	 * @param type the {@link Class} of the object.
	 * @param args the arguments of the construction of the object
	 * @return the new <X> instance
	 */
	protected static <X> X instance(Class<X> type, Object... args) {
		try {
			return type.getDeclaredConstructor(getClasses(args)).newInstance(args);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	/**
	 * Returns an array of the classes of the object array argument.
	 * @param objects the array of the objects t
	 * @return
	 */
	static Class<?>[] getClasses(Object... objects) {
		Class<?>[] types = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}
		return types;
	}
}
