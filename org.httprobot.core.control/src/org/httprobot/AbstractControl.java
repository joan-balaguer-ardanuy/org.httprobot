package org.httprobot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.httprobot.event.EventArgs;

public abstract class AbstractControl<T extends Message> 
	extends AbstractListener<Control>
		implements Control {

	/**
	 * -8721125055676944109L
	 */
	private static final long serialVersionUID = -8721125055676944109L;

	T message;
	Map<Data,Object> data;
	
	Data currentKey;
	Object currentValue;
	
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
	
	public AbstractControl() {
		super();
	}
	public AbstractControl(T message) {
		super(message.getName());
		this.message = message;
		initialize();
	}
	public AbstractControl(T message, Control parent) {
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
		if (message == null)
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
				// Check if control has child XML message controls
				if (hasChildren()) {
					// Iterate through child XML message controls
					while (hasNext()) {
						// Get next child XML message control
						Control control = next();
						control.load();
					}
					// Set control ready to be iterated again.
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