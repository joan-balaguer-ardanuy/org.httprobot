package org.httprobot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.httprobot.Enums.ControlEventType;
import org.httprobot.Enums.Data;
import org.httprobot.event.ControlEventArgs;

public abstract class Control<TMessage extends XML> 
	extends XML
		implements ControlListener, Iterator<ControlListener>, DataMapping<Data, Object> {

	/**
	 * -8721125055676944109L
	 */
	private static final long serialVersionUID = -8721125055676944109L;

	TMessage message;
	ControlListener parent;
	
	int currentControlIndex;
	List<ControlListener> childControls;
	
	Set<ControlListener> controlEventListeners;

	Map<Data,Object> data;
	
	Data currentKey;
	Object currentValue;
	
	public TMessage getMessage() {
		return message;
	}
	public void setMessage(TMessage message) {
		this.message = message;
	}
	public ControlListener getParent() {
		return parent;
	}
	public void setParent(ControlListener parent) {
		this.parent = parent;
	}
	
	public List<ControlListener> getChildControls() {
		return childControls;
	}
	public void setChildControls(List<ControlListener> childControls) {
		this.childControls = childControls;
	}
	public Control() {
		super();
		
		currentControlIndex = 0;
		childControls = new ArrayList<ControlListener>();
	
		controlEventListeners = new LinkedHashSet<ControlListener>();
		
		data = new HashMap<Data, Object>();
		
		addControlEventListener(this);
	}
	public Control(TMessage message, ControlListener parent) {
		super(message.getUuid());
		
		setMessage(message);
		setParent(parent);
		addCommandListener(parent);
		
		currentControlIndex = 0;
		childControls = new ArrayList<ControlListener>();
		
		controlEventListeners = new LinkedHashSet<ControlListener>();
		
		data = new HashMap<Data, Object>();
		
		addControlEventListener(this);
		addControlEventListener(parent);
		
		ControlEvent(new ControlEventArgs(this, message, ControlEventType.INIT));
	}
	public final void addControlEventListener(ControlListener listener) {
		controlEventListeners.add(listener);
	}
	public final void removeControlListener(ControlListener listener) {
		controlEventListeners.remove(listener);
	}

	public void loadControl() {
		if (message == null)
			throw new NullPointerException("Control.LoadControl: Control's message hasn't been initialized");

		put(Data.UUID, message.getUuid());
		
		ControlEvent(new ControlEventArgs(this, message, ControlEventType.LOAD));
	}
	
	@Override
	public abstract void OnControlInitialized(ControlEventArgs e);

	@Override
	public abstract void OnControlLoaded(ControlEventArgs e);

	
	protected final void ControlEvent(ControlEventArgs e) {
		for(ControlListener listener : controlEventListeners) {
			switch (e.getControlEventType()) {
			case INIT:
				listener.OnControlInitialized(e);
				break;
			case LOAD:
				listener.OnControlLoaded(e);
				break;
			default:
				break;
			}

		}
	}
	
	public boolean hasChildControls()
	{
		boolean isEmpty = (childControls != null ? childControls.isEmpty() : true);
		return !isEmpty;
	}
	@Override
	public boolean hasNext() {
		return currentControlIndex < childControls.size();
	}
	@Override
	public ControlListener next() {
		ControlListener control = childControls.get(currentControlIndex);
		currentControlIndex++;
		return control;
	}
	public void addChildControl(ControlListener child) {
		childControls.add(child);
	}
	public void reset() {
		this.currentControlIndex = 0;
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