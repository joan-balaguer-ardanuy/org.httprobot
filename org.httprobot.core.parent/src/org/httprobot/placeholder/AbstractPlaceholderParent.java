package org.httprobot.placeholder;

import org.httprobot.Control;
import org.httprobot.ParentListener;
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
	
	protected ContainsParent containsParent;
	protected EqualsParent equalsParent;
	protected TrimParent trimParent;
	protected ReplaceParent replaceParent;
	protected ConcatParent concatParent;
	protected SubstringParent substringParent;
	protected TryParseParent tryParseParent;
	protected StartsWithParent startsWithParent;
	protected EndsWithParent endsWithParent;
	protected ToUpperCaseParent toUpperCaseParent;
	protected ToLowerCaseParent toLowerCaseParent;
	
	public AbstractPlaceholderParent() {
		super();
	}
	public AbstractPlaceholderParent(AbstractPlaceholder message, Class<T> type, ParentListener parent) {
		super(message, type, parent);
	}
	
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContainsControl) {
				Contains message = ContainsControl.class.cast(e.getSource()).getMessage();
				containsParent = new ContainsParent(message, this);
				addChildManager(containsParent);
			} else if(e.getSource() instanceof EqualsControl) {
				Equals message = EqualsControl.class.cast(e.getSource()).getMessage();
				equalsParent = new EqualsParent(message, this);
				addChildManager(equalsParent);
			} else if(e.getSource() instanceof TrimControl) {
				Trim message = TrimControl.class.cast(e.getSource()).getMessage();
				trimParent = new TrimParent(message, this);
				addChildManager(trimParent);
			} else if(e.getSource() instanceof ReplaceControl) {
				Replace message = ReplaceControl.class.cast(e.getSource()).getMessage();
				replaceParent = new ReplaceParent(message, this);
				addChildManager(replaceParent);
			} else if(e.getSource() instanceof ConcatControl) {
				Concat message = ConcatControl.class.cast(e.getSource()).getMessage();
				concatParent = new ConcatParent(message, this);
				addChildManager(concatParent);
			} else if(e.getSource() instanceof SubstringControl) {
				Substring message = SubstringControl.class.cast(e.getSource()).getMessage();
				substringParent = new SubstringParent(message, this);
				addChildManager(substringParent);
			} else if(e.getSource() instanceof TryParseControl) {
				TryParse message = TryParseControl.class.cast(e.getSource()).getMessage();
				tryParseParent = new TryParseParent(message, this);
				addChildManager(tryParseParent);
			} else if(e.getSource() instanceof StartsWithControl) {
				StartsWith message = StartsWithControl.class.cast(e.getSource()).getMessage();
				startsWithParent = new StartsWithParent(message, this);
				addChildManager(startsWithParent);
			} else if(e.getSource() instanceof EndsWithControl) {
				EndsWith message = EndsWithControl.class.cast(e.getSource()).getMessage();
				endsWithParent = new EndsWithParent(message, this);
				addChildManager(endsWithParent);
			} else if(e.getSource() instanceof ToUpperCaseControl) {
				ToUpperCase message = ToUpperCaseControl.class.cast(e.getSource()).getMessage();
				toUpperCaseParent = new ToUpperCaseParent(message, this);
				addChildManager(toUpperCaseParent);
			} else if(e.getSource() instanceof ToLowerCaseControl) {
				ToLowerCase message = ToLowerCaseControl.class.cast(e.getSource()).getMessage();
				toLowerCaseParent = new ToLowerCaseParent(message, this);
				addChildManager(toLowerCaseParent);
			}
			break;
		default:
			break;
		}
	}
}