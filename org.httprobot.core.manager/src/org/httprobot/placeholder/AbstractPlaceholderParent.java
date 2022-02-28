package org.httprobot.placeholder;

import org.httprobot.Control;
import org.httprobot.ManagerListener;
import org.httprobot.MappingParent;
import org.httprobot.AbstractPlaceholder;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.ContainsParent;
import org.httprobot.placeholder.string.EndsWith;
import org.httprobot.placeholder.string.EndsWithControl;
import org.httprobot.placeholder.string.EndsWithParent;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.EqualsParent;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.TrimParent;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.ReplaceParent;
import org.httprobot.placeholder.string.StartsWith;
import org.httprobot.placeholder.string.StartsWithControl;
import org.httprobot.placeholder.string.StartsWithParent;
import org.httprobot.placeholder.string.Concat;
import org.httprobot.placeholder.string.ConcatControl;
import org.httprobot.placeholder.string.ConcatParent;
import org.httprobot.placeholder.string.Substring;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.SubstringParent;
import org.httprobot.placeholder.string.ToLowerCase;
import org.httprobot.placeholder.string.ToLowerCaseControl;
import org.httprobot.placeholder.string.ToLowerCaseParent;
import org.httprobot.placeholder.string.ToUpperCase;
import org.httprobot.placeholder.string.ToUpperCaseControl;
import org.httprobot.placeholder.string.ToUpperCaseParent;
import org.httprobot.placeholder.string.TryParse;
import org.httprobot.placeholder.string.TryParseControl;
import org.httprobot.placeholder.string.TryParseParent;

public abstract class AbstractPlaceholderParent<K, V, T extends Control<?>>
	extends MappingParent<K, V, T> {

	/**
	 * -1728687948431462444L
	 */
	private static final long serialVersionUID = -1728687948431462444L;
	
	protected ContainsParent containsManager;
	protected EqualsParent equalsManager;
	protected TrimParent trimManager;
	protected ReplaceParent replaceManager;
	protected ConcatParent concatManager;
	protected SubstringParent substringManager;
	protected TryParseParent tryParseManager;
	protected StartsWithParent startsWithManager;
	protected EndsWithParent endsWithManager;
	protected ToUpperCaseParent toUpperCaseManager;
	protected ToLowerCaseParent toLowerCaseManager;
	
	public AbstractPlaceholderParent() {
		super();
	}
	public AbstractPlaceholderParent(AbstractPlaceholder message, Class<T> type, ManagerListener parent) {
		super(message, type, parent);
	}
	
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTAINS_CONTROL_LOADED:
			if(e.getSource() instanceof ContainsControl) {
				Contains message = ContainsControl.class.cast(e.getSource()).getMessage();
				containsManager = new ContainsParent(message, this);
				addChildManager(containsManager);
			}
			break;
		case EQUALS_CONTROL_LOADED:
			if(e.getSource() instanceof EqualsControl) {
				Equals message = EqualsControl.class.cast(e.getSource()).getMessage();
				equalsManager = new EqualsParent(message, this);
				addChildManager(equalsManager);
			}
			break;
		case TRIM_CONTROL_LOADED:
			if(e.getSource() instanceof TrimControl) {
				Trim message = TrimControl.class.cast(e.getSource()).getMessage();
				trimManager = new TrimParent(message, this);
				addChildManager(trimManager);
			}
			break;
		case REPLACE_CONTROL_LOADED:
			if(e.getSource() instanceof ReplaceControl) {
				Replace message = ReplaceControl.class.cast(e.getSource()).getMessage();
				replaceManager = new ReplaceParent(message, this);
				addChildManager(replaceManager);
			}
			break;
		case CONCAT_CONTROL_LOADED:
			if(e.getSource() instanceof ConcatControl) {
				Concat message = ConcatControl.class.cast(e.getSource()).getMessage();
				concatManager = new ConcatParent(message, this);
				addChildManager(concatManager);
			}
			break;
		case SUBSTRING_CONTROL_LOADED:
			if(e.getSource() instanceof SubstringControl) {
				Substring message = SubstringControl.class.cast(e.getSource()).getMessage();
				substringManager = new SubstringParent(message, this);
				addChildManager(substringManager);
			}
			break;
		case TRY_PARSE_CONTROL_LOADED:
			if(e.getSource() instanceof TryParseControl) {
				TryParse message = TryParseControl.class.cast(e.getSource()).getMessage();
				tryParseManager = new TryParseParent(message, this);
				addChildManager(tryParseManager);
			}
			break;
		case STARTS_WITH_CONTROL_LOADED:
			if(e.getSource() instanceof StartsWithControl) {
				StartsWith message = StartsWithControl.class.cast(e.getSource()).getMessage();
				startsWithManager = new StartsWithParent(message, this);
				addChildManager(startsWithManager);
			}
			break;
		case ENDS_WITH_CONTROL_LOADED:
			if(e.getSource() instanceof EndsWithControl) {
				EndsWith message = EndsWithControl.class.cast(e.getSource()).getMessage();
				endsWithManager = new EndsWithParent(message, this);
				addChildManager(endsWithManager);
			}
			break;
		case TO_UPPER_CASE_CONTROL_LOADED:
			if(e.getSource() instanceof ToUpperCaseControl) {
				ToUpperCase message = ToUpperCaseControl.class.cast(e.getSource()).getMessage();
				toUpperCaseManager = new ToUpperCaseParent(message, this);
				addChildManager(toUpperCaseManager);
			}
			break;
		case TO_LOWER_CASE_CONTROL_LOADED:
			if(e.getSource() instanceof ToLowerCaseControl) {
				ToLowerCase message = ToLowerCaseControl.class.cast(e.getSource()).getMessage();
				toLowerCaseManager = new ToLowerCaseParent(message, this);
				addChildManager(toLowerCaseManager);
			}
			break;
		default:
			break;
		}
	}
}