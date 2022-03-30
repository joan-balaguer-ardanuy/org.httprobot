package org.httprobot.placeholder;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.Message;
import org.httprobot.Control;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.Concat;
import org.httprobot.placeholder.string.ConcatControl;
import org.httprobot.placeholder.string.ConcatParent;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.ContainsParent;
import org.httprobot.placeholder.string.EndsWith;
import org.httprobot.placeholder.string.EndsWithControl;
import org.httprobot.placeholder.string.EndsWithParent;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.EqualsParent;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.ReplaceParent;
import org.httprobot.placeholder.string.StartsWith;
import org.httprobot.placeholder.string.StartsWithControl;
import org.httprobot.placeholder.string.StartsWithParent;
import org.httprobot.placeholder.string.Substring;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.SubstringParent;
import org.httprobot.placeholder.string.ToLowerCase;
import org.httprobot.placeholder.string.ToLowerCaseControl;
import org.httprobot.placeholder.string.ToLowerCaseParent;
import org.httprobot.placeholder.string.ToUpperCase;
import org.httprobot.placeholder.string.ToUpperCaseControl;
import org.httprobot.placeholder.string.ToUpperCaseParent;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.TrimParent;
import org.httprobot.placeholder.string.TryParse;
import org.httprobot.placeholder.string.TryParseControl;
import org.httprobot.placeholder.string.TryParseParent;

public abstract class AbstractOperatorParent<K, V>
	extends ParentEntry<K, V> {

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
	
	public AbstractOperatorParent() {
		super();
	}
	public AbstractOperatorParent(Message message, Class<? extends Control> type, Parent parent) {
		super(message, type, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContainsControl) {
				Contains message = ContainsControl.class.cast(e.getSource()).getMessage();
				containsParent = new ContainsParent(message, this);
				addChild(containsParent);
			} else if(e.getSource() instanceof EqualsControl) {
				Equals message = EqualsControl.class.cast(e.getSource()).getMessage();
				equalsParent = new EqualsParent(message, this);
				addChild(equalsParent);
			} else if(e.getSource() instanceof TrimControl) {
				Trim message = TrimControl.class.cast(e.getSource()).getMessage();
				trimParent = new TrimParent(message, this);
				addChild(trimParent);
			} else if(e.getSource() instanceof ReplaceControl) {
				Replace message = ReplaceControl.class.cast(e.getSource()).getMessage();
				replaceParent = new ReplaceParent(message, this);
				addChild(replaceParent);
			} else if(e.getSource() instanceof ConcatControl) {
				Concat message = ConcatControl.class.cast(e.getSource()).getMessage();
				concatParent = new ConcatParent(message, this);
				addChild(concatParent);
			} else if(e.getSource() instanceof SubstringControl) {
				Substring message = SubstringControl.class.cast(e.getSource()).getMessage();
				substringParent = new SubstringParent(message, this);
				addChild(substringParent);
			} else if(e.getSource() instanceof TryParseControl) {
				TryParse message = TryParseControl.class.cast(e.getSource()).getMessage();
				tryParseParent = new TryParseParent(message, this);
				addChild(tryParseParent);
			} else if(e.getSource() instanceof StartsWithControl) {
				StartsWith message = StartsWithControl.class.cast(e.getSource()).getMessage();
				startsWithParent = new StartsWithParent(message, this);
				addChild(startsWithParent);
			} else if(e.getSource() instanceof EndsWithControl) {
				EndsWith message = EndsWithControl.class.cast(e.getSource()).getMessage();
				endsWithParent = new EndsWithParent(message, this);
				addChild(endsWithParent);
			} else if(e.getSource() instanceof ToUpperCaseControl) {
				ToUpperCase message = ToUpperCaseControl.class.cast(e.getSource()).getMessage();
				toUpperCaseParent = new ToUpperCaseParent(message, this);
				addChild(toUpperCaseParent);
			} else if(e.getSource() instanceof ToLowerCaseControl) {
				ToLowerCase message = ToLowerCaseControl.class.cast(e.getSource()).getMessage();
				toLowerCaseParent = new ToLowerCaseParent(message, this);
				addChild(toLowerCaseParent);
			}
			break;
		default:
			break;
		}
	}
}