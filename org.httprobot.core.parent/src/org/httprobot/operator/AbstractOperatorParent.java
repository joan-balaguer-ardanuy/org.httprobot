package org.httprobot.operator;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.Message;
import org.httprobot.Control;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.string.Concat;
import org.httprobot.operator.string.ConcatControl;
import org.httprobot.operator.string.ConcatParent;
import org.httprobot.operator.string.Contains;
import org.httprobot.operator.string.ContainsControl;
import org.httprobot.operator.string.ContainsParent;
import org.httprobot.operator.string.EndsWith;
import org.httprobot.operator.string.EndsWithControl;
import org.httprobot.operator.string.EndsWithParent;
import org.httprobot.operator.string.Equals;
import org.httprobot.operator.string.EqualsControl;
import org.httprobot.operator.string.EqualsParent;
import org.httprobot.operator.string.Replace;
import org.httprobot.operator.string.ReplaceControl;
import org.httprobot.operator.string.ReplaceParent;
import org.httprobot.operator.string.StartsWith;
import org.httprobot.operator.string.StartsWithControl;
import org.httprobot.operator.string.StartsWithParent;
import org.httprobot.operator.string.Substring;
import org.httprobot.operator.string.SubstringControl;
import org.httprobot.operator.string.SubstringParent;
import org.httprobot.operator.string.ToLowerCase;
import org.httprobot.operator.string.ToLowerCaseControl;
import org.httprobot.operator.string.ToLowerCaseParent;
import org.httprobot.operator.string.ToUpperCase;
import org.httprobot.operator.string.ToUpperCaseControl;
import org.httprobot.operator.string.ToUpperCaseParent;
import org.httprobot.operator.string.Trim;
import org.httprobot.operator.string.TrimControl;
import org.httprobot.operator.string.TrimParent;
import org.httprobot.operator.string.TryParse;
import org.httprobot.operator.string.TryParseControl;
import org.httprobot.operator.string.TryParseParent;

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