package org.httprobot.placeholder;

import org.httprobot.Control;
import org.httprobot.Manager;
import org.httprobot.Placeholder;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.ContainsManager;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.EqualsManager;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.TrimManager;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.ReplaceManager;
import org.httprobot.placeholder.string.Split;
import org.httprobot.placeholder.string.SplitControl;
import org.httprobot.placeholder.string.SplitManager;
import org.httprobot.placeholder.string.Substring;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.SubstringManager;
import org.httprobot.placeholder.string.TryParse;
import org.httprobot.placeholder.string.TryParseControl;
import org.httprobot.placeholder.string.TryParseManager;

public abstract class AbstractPlaceholderManager<K,T extends Control<?>>
	extends Manager<T> 
		implements java.util.Map.Entry<K,InputField> {

	/**
	 * -1728687948431462444L
	 */
	private static final long serialVersionUID = -1728687948431462444L;

	K key;
	InputField value;
	
	ContainsManager containsManager;
	EqualsManager equalsManager;
	TrimManager trimManager;
	ReplaceManager replaceManager;
	SplitManager splitManager;
	SubstringManager substringManager;
	TryParseManager tryParseManager;
	
	@Override
	public K getKey() {
		return key;
	}
	public K setKey(K key) {
		K oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	@Override
	public InputField getValue() {
		return value;
	}
	@Override
	public InputField setValue(InputField value) {
		InputField oldValue = this.value;
		if(value.getValue() != null) {
			if(containsManager != null) {
				containsManager.setKey(value.getValue().toString());
			} else if(equalsManager != null) {
				equalsManager.setKey(value.getValue().toString());
			} else if(trimManager != null) {
				trimManager.setKey(value.getValue().toString());
			} else if(replaceManager != null) {
				replaceManager.setKey(value.getValue().toString());
			} else if(splitManager != null) {
				splitManager.setKey(value.getValue().toString());
			} else if(substringManager != null) {
				substringManager.setKey(value.getValue().toString());
			} else if(tryParseManager != null) {
				tryParseManager.setKey(value.getValue().toString());
			}
		}
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
	
	public AbstractPlaceholderManager() {
		super();
	}
	public AbstractPlaceholderManager(Placeholder message, Class<T> type, Manager<?> parent) {
		super(message, type, parent);
	}

	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case FINISHED:
			if(e.getSource().equals(this)) {
				if(containsManager != null) {
					containsManager.setValue(value);
				} else if(equalsManager != null) {
					equalsManager.setValue(value);
				} else if(trimManager != null) {
					trimManager.setValue(value);
				} else if(replaceManager != null) {
					replaceManager.setValue(value);
				} else if(splitManager != null) {
					splitManager.setValue(value);
				} else if(substringManager != null) {
					substringManager.setValue(value);
				} else if(tryParseManager != null) {
					tryParseManager.setValue(value);
				}	
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTAINS_CONTROL_LOADED:
			if(e.getSource() instanceof ContainsControl) {
				Contains message = ContainsControl.class.cast(e.getSource()).getMessage();
				containsManager = new ContainsManager(message, this);
				addChildManager(containsManager);
			}
			break;
		case EQUALS_CONTROL_LOADED:
			if(e.getSource() instanceof EqualsControl) {
				Equals message = EqualsControl.class.cast(e.getSource()).getMessage();
				equalsManager = new EqualsManager(message, this);
				addChildManager(equalsManager);
			}
			break;
		case TRIM_CONTROL_LOADED:
			if(e.getSource() instanceof TrimControl) {
				Trim message = TrimControl.class.cast(e.getSource()).getMessage();
				trimManager = new TrimManager(message, this);
				addChildManager(trimManager);
			}
			break;
		case REPLACE_CONTROL_LOADED:
			if(e.getSource() instanceof ReplaceControl) {
				Replace message = ReplaceControl.class.cast(e.getSource()).getMessage();
				replaceManager = new ReplaceManager(message, this);
				addChildManager(replaceManager);
			}
			break;
		case SPLIT_CONTROL_LOADED:
			if(e.getSource() instanceof SplitControl) {
				Split message = SplitControl.class.cast(e.getSource()).getMessage();
				splitManager = new SplitManager(message, this);
				addChildManager(splitManager);
			}
			break;
		case SUBSTRING_CONTROL_LOADED:
			if(e.getSource() instanceof SubstringControl) {
				Substring message = SubstringControl.class.cast(e.getSource()).getMessage();
				substringManager = new SubstringManager(message, this);
				addChildManager(substringManager);
			}
			break;
		case TRY_PARSE_CONTROL_LOADED:
			if(e.getSource() instanceof TryParseControl) {
				TryParse message = TryParseControl.class.cast(e.getSource()).getMessage();
				tryParseManager = new TryParseManager(message, this);
				addChildManager(tryParseManager);
			}
			break;
		default:
			break;
		}
	}
}