package org.httprobot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.httprobot.event.EventArgs;

/**
 * Abstract control class. Inherits {@link AbstractListener}.
 * And it is also a {@link Control}.
 * @author joan
 *
 */
public abstract class AbstractControl
	extends AbstractListener<Control>
		implements Control {

	/**
	 * -8721125055676944109L
	 */
	private static final long serialVersionUID = -8721125055676944109L;

	/**
	 * The XML messsage.
	 */
	XML message;
	/**
	 * The loaded data.
	 */
	Map<Data,Object> data;
	
	/**
	 * Current key index
	 */
	Data currentKey;
	/**
	 * Currrent value mapped on currnet key
	 */
	Object currentValue;
	
	public XML getMessage() {
		return message;
	}

	/**
	 * {@link AbstractControl} default class contructor.
	 */
	public AbstractControl() {
		super();
	}
	/**
	 * {@link AbstractControl} class constructor.
	 * @param message {@link XML} the message
	 */
	public AbstractControl(XML message) {
		super(message.getName());
		this.message = message;
		initialize();
	}
	/**
	 * {@link AbstractControl} class constructor.
	 * @param message {@link XML} the message
	 * @param parent {@link Control} the parent instance
	 */
	public AbstractControl(XML message, Control parent) {
		super(parent, message.getName());
		this.message = message;
		initialize();
	}
	@Override
	public void initialize() {
		data = new HashMap<Data, Object>();		
		OnEventReceived(new EventArgs(this, message, EventType.CONTROL_INITIALIZED));
	}
	@Override
	public void load() {
		if (data == null)
			throw new NullPointerException("Control.load: Control's message hasn't been initialized");

		put(Data.PARENT, getParent());
		put(Data.CHILDREN, getChildren());
		
		OnEventReceived(new EventArgs(this, message, EventType.CONTROL_LOADED));
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				// check if control has child XML message controls
				if (hasChildren()) {
					// iterate through child XML message controls
					while (hasNext()) {
						// get next child XML message control
						Control control = next();
						control.load();
					}
					// set control ready to be iterated again.
					reset();
				}
			}
			break;

		default:
			break;
		}
	}
	@Override
	public int size() {
		return data.size();
	}
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}
	@Override
	public Object get(Object key) {
		return data.get(key);
	}
	@Override
	public Object put(Data key, Object value) {
		setKey(key);
		setValue(value);
		return data.put(key, value);
	}
	@Override
	public Object remove(Object key) {
		return data.remove(key);
	}
	@Override
	public void putAll(Map<? extends Data, ? extends Object> m) {
		for(Data key : m.keySet()) {
			put(key, m.get(key));
		}
	}
	@Override
	public void clear() {
		data.clear();
	}
	@Override
	public Set<Data> keySet() {
		return data.keySet();
	}
	@Override
	public Collection<Object> values() {
		return data.values();
	}
	@Override
	public Set<Map.Entry<Data, Object>> entrySet() {
		return data.entrySet();
	}
	@Override
	public Iterator<Data> iterator() {
		return data.keySet().iterator();
	}
	@Override
	public Data getKey() {
		return currentKey;
	}
	@Override
	public Data setKey(Data key) {
		Data oldKey = currentKey;
		currentKey = key;
		return oldKey;
	}
	@Override
	public Object getValue() {
		return currentValue;
	}
	@Override
	public Object setValue(Object value) {
		Object oldValue = currentValue;
		currentValue = value;
		return oldValue;
	}
}