package org.httprobot.configuration;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRootControl;
import org.httprobot.datatype.DataSource;
import org.httprobot.datatype.DataSourceControl;
import org.httprobot.event.EventArgs;

/**
 * It is the parent control for all the messages, except {@link ServiceConnection}.
 * @author joan
 *
 */
@XmlRootElement
public final class SourceControl
	extends AbstractControl {

	/**
	 * 352106218223736293L
	 */
	private static final long serialVersionUID = 352106218223736293L;

	/**
	 * The content type root control.
	 */
	ContentTypeRootControl contentTypeRootControl;
	/**
	 * The data source control.
	 */
	LinkedHashSet<DataSourceControl> dataSourceControl;
	
	/**
	 * Returns the {@link ContentTypeRootControl} of current instance.
	 * @return the {@link ContentTypeRootControl} of current instance.
	 */
	@XmlElement
	public ContentTypeRootControl getContentTypeRootControl() {
		return contentTypeRootControl;
	}
	/**
	 * Sets the {@link ContentTypeRootControl} of current instance.
	 * @param contentTypeRootControl the {@link ContentTypeRootControl} of current instance.
	 */
	public void setContentTypeRootControl(ContentTypeRootControl contentTypeRootControl) {
		this.contentTypeRootControl = contentTypeRootControl;
	}
	/**
	 * Returns the {@link LinkedHashSet} of {@link DataSourceControl}.
	 * @return the {@link LinkedHashSet} of {@link DataSourceControl}.
	 */
	@XmlElement
	public LinkedHashSet<DataSourceControl> getDataSourceControl() {
		return dataSourceControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link DataSourceControl}.
	 * @param dataSourceControls the {@link LinkedHashSet} of {@link DataSourceControl}.
	 */
	public void setDataSourceControl(LinkedHashSet<DataSourceControl> dataSourceControls) {
		this.dataSourceControl = dataSourceControls;
	}
	@Override
	@XmlElement
	public Source getMessage() {
		return (Source) super.getMessage();
	}
	
	/**
	 * {@link SourceControl} default class constructor.
	 */
	public SourceControl() {
		super();
	}
	/**
	 * {@link SourceControl} class constructor.
	 * @param message {@link Source} the message
	 */
	public SourceControl(Source message) {
		super(message);
	}
	/**
	 * {@link SourceControl} class constructor.
	 * @param message {@link Source} the message
	 * @param parent {@link Control} the parent instance
	 */
	public SourceControl(Source message, Control parent) {
		super(message, parent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				// cast source XML message
				Source source = (Source) e.getValue();
				// check if content type root exists else throw exception
				if (source.getContentTypeRoot() != null) {
					// initialize ContentTypeRoot message control.
					new ContentTypeRootControl(source.getContentTypeRoot(), this);
				} else {
					throw new Error("SourceControl.OnEventReceived: ContentTypeRoot XML message missing");
				}
				// check if data sources exists else throw exception
				if (source.getDataSource() != null) {
					// instance data source control message set
					dataSourceControl = new LinkedHashSet<DataSourceControl>();
					// for each dataSource in source XML message
					for (DataSource dataSource : source.getDataSource()) {
						// this instance listens for it's initialization event
						new DataSourceControl(dataSource, this);
					}
				} else {
					throw new Error("SourceControl.OnEventReceived: DataSource XML message missing");
				}
			} else if (e.getSource() instanceof ContentTypeRootControl) {
				// set new control
				contentTypeRootControl = (ContentTypeRootControl) e.getSource();
				// store control
				addChild(contentTypeRootControl);
			} else if (e.getSource() instanceof DataSourceControl) {			
				DataSourceControl dataSourceControl = (DataSourceControl) e.getSource();
				// store control
				getDataSourceControl().add(dataSourceControl);
				addChild(dataSourceControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource() instanceof DataSourceControl) {
				// check if control has been initialitzed in this control
				if (getChildren().contains(e.getSource())) {
					// cast event's value
					DataSource dataSource = (DataSource) e.getValue();
					// check if data source's data exists
					if(get(Data.DATA_SOURCE) == null) {
						// instance new set
						Set<Message> set = new LinkedHashSet<Message>();
						// add first data source value to set
						set.add(dataSource);
						// set data
						put(Data.DATA_SOURCE, set);
					} else {
						// add message to data
						Object set = get(Data.DATA_SOURCE);
						((Set<Message>) set).add(dataSource);
					}
				}
				// look for content type root control
			} else if (e.getSource() instanceof ContentTypeRootControl) {
				// check if source is from current instance
				if (getChildren().contains(e.getSource())) {
					// store content type root XML message
					put(Data.CONTENT_TYPE_ROOT, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}