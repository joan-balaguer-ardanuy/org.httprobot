package org.httprobot.placeholder;

import org.httprobot.Control;
import org.httprobot.Manager;
import org.httprobot.MappingManager;
import org.httprobot.AbstractPlaceholder;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.ContainsManager;
import org.httprobot.placeholder.string.EndsWith;
import org.httprobot.placeholder.string.EndsWithControl;
import org.httprobot.placeholder.string.EndsWithManager;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.EqualsManager;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.TrimManager;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.ReplaceManager;
import org.httprobot.placeholder.string.StartsWith;
import org.httprobot.placeholder.string.StartsWithControl;
import org.httprobot.placeholder.string.StartsWithManager;
import org.httprobot.placeholder.string.Concat;
import org.httprobot.placeholder.string.ConcatControl;
import org.httprobot.placeholder.string.ConcatManager;
import org.httprobot.placeholder.string.Substring;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.SubstringManager;
import org.httprobot.placeholder.string.ToLowerCase;
import org.httprobot.placeholder.string.ToLowerCaseControl;
import org.httprobot.placeholder.string.ToLowerCaseManager;
import org.httprobot.placeholder.string.ToUpperCase;
import org.httprobot.placeholder.string.ToUpperCaseControl;
import org.httprobot.placeholder.string.ToUpperCaseManager;
import org.httprobot.placeholder.string.TryParse;
import org.httprobot.placeholder.string.TryParseControl;
import org.httprobot.placeholder.string.TryParseManager;

public abstract class AbstractPlaceholderManager<V,T extends Control<?>>
	extends MappingManager<InputField, V, T> {

	/**
	 * -1728687948431462444L
	 */
	private static final long serialVersionUID = -1728687948431462444L;
	
	protected ContainsManager containsManager;
	protected EqualsManager equalsManager;
	protected TrimManager trimManager;
	protected ReplaceManager replaceManager;
	protected ConcatManager concatManager;
	protected SubstringManager substringManager;
	protected TryParseManager tryParseManager;
	protected StartsWithManager startsWithManager;
	protected EndsWithManager endsWithManager;
	protected ToUpperCaseManager toUpperCaseManager;
	protected ToLowerCaseManager toLowerCaseManager;
	
	public AbstractPlaceholderManager() {
		super();
	}
	public AbstractPlaceholderManager(AbstractPlaceholder message, Class<T> type, Manager<?> parent) {
		super(message, type, parent);
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
		case CONCAT_CONTROL_LOADED:
			if(e.getSource() instanceof ConcatControl) {
				Concat message = ConcatControl.class.cast(e.getSource()).getMessage();
				concatManager = new ConcatManager(message, this);
				addChildManager(concatManager);
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
		case STARTS_WITH_CONTROL_LOADED:
			if(e.getSource() instanceof StartsWithControl) {
				StartsWith message = StartsWithControl.class.cast(e.getSource()).getMessage();
				startsWithManager = new StartsWithManager(message, this);
				addChildManager(startsWithManager);
			}
			break;
		case ENDS_WITH_CONTROL_LOADED:
			if(e.getSource() instanceof EndsWithControl) {
				EndsWith message = EndsWithControl.class.cast(e.getSource()).getMessage();
				endsWithManager = new EndsWithManager(message, this);
				addChildManager(endsWithManager);
			}
			break;
		case TO_UPPER_CASE_CONTROL_LOADED:
			if(e.getSource() instanceof ToUpperCaseControl) {
				ToUpperCase message = ToUpperCaseControl.class.cast(e.getSource()).getMessage();
				toUpperCaseManager = new ToUpperCaseManager(message, this);
				addChildManager(toUpperCaseManager);
			}
			break;
		case TO_LOWER_CASE_CONTROL_LOADED:
			if(e.getSource() instanceof ToLowerCaseControl) {
				ToLowerCase message = ToLowerCaseControl.class.cast(e.getSource()).getMessage();
				toLowerCaseManager = new ToLowerCaseManager(message, this);
				addChildManager(toLowerCaseManager);
			}
			break;
		default:
			break;
		}
	}
}