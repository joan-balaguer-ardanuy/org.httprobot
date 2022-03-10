package org.httprobot;

import java.util.LinkedHashSet;
import java.util.Set;

import org.httprobot.event.EventArgs;

public abstract class AbstractEventListener
	extends XML
		implements EventListener {

	/**
	 * 2951712424683872158L
	 */
	private static final long serialVersionUID = 2951712424683872158L;
	/**
	 * The event listeners set.
	 */
	Set<EventListener> eventListeners;
	
	public AbstractEventListener() {
		super();
		this.eventListeners = new LinkedHashSet<EventListener>();
	}
	
	/**
	 * Adds new command listener to the command listeners set.
	 * @param listener {@link Listener} the new listener to be added.
	 */
	public final void addEventListener(EventListener listener) {
		eventListeners.add(listener);
	}
	/**
	 * Removes old command listener from the command listenrs set.
	 * @param listener {@link Listener} the old listener to be removed.
	 */
	public final void removeEventListener(EventListener listener) {
		eventListeners.remove(listener);
	}
	/**
	 * Fires {@link Listener} event to all command listeners added in the set.
	 * @param e {@link CommandEventArgs} the arguments of the event
	 */
	protected void SendEvent(EventArgs e) {
		for(EventListener listener : eventListeners) {
			// send event to all event listeners
			listener.OnEventReceived(e);
		}
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		// stream event
		SendEvent(e);		
	}
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