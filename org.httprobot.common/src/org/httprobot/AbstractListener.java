package org.httprobot;

import java.util.List;


/**
 * Abstract listener class.
 * It's the implementation of {@link Listener} interface.
 * @author joan
 *
 */
public abstract class AbstractListener<T extends Listener<T>> 
	extends AbstractEventListener
		implements Listener<T> {

	/**
	 * 1182412397820363386L
	 */
	private static final long serialVersionUID = 1182412397820363386L;
	
	/**
	 * The parent instance
	 */
	T parent;

	List<T> children;
	
	int currentChildIndex;
	
	@Override
	public T getParent() {
		return parent;
	}
	@Override
	public void setParent(T parent) {
		this.parent = parent;
	}
	@Override
	public List<T> getChildren() {
		return children;
	}
	@Override
	public void setChildren(List<T> children) {
		this.children = children;
	}
	
	/**
	 * {@link AbstractListener} default class constructor.
	 */
	public AbstractListener() {
		super();
	}
	/**
	 * {@link AbstractListener} default class constructor.
	 */
	public AbstractListener(String name) {
		super(name);
	}
	/**
	 * {@link AbstractListener} class constructor.
	 */
	public AbstractListener(T parent) {
		super();
		this.parent = parent;
		addEventListener(parent);
	}
	/**
	 * {@link AbstractListener} class constructor.
	 */
	public AbstractListener(T parent, String name) {
		super(name);
		this.parent = parent;
		addEventListener(parent);
	}
	
	public boolean hasChildren() {
		boolean isEmpty = (children != null ? children.isEmpty() : true);
		return !isEmpty;
	}
	public void addChild(T child) {
		children.add(child);
	}
	public void reset() {
		this.currentChildIndex = 0;
	}
	@Override
	public boolean hasNext() {
		return currentChildIndex < children.size();
	}
	@Override
	public T next() {
		T child = children.get(currentChildIndex);
		currentChildIndex++;
		return child;
	}
}