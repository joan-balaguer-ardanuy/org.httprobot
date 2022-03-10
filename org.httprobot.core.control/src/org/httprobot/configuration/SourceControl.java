package org.httprobot.configuration;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.content.ContentTypeRootControl;
import org.httprobot.datatype.DataSource;
import org.httprobot.datatype.DataSourceControl;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class SourceControl
	extends AbstractControl<Source> {

	/**
	 * 352106218223736293L
	 */
	private static final long serialVersionUID = 352106218223736293L;

	ContentTypeRootControl contentTypeRootControl;
	LinkedHashSet<DataSourceControl> dataSourceControl;
	
	@XmlElement
	public ContentTypeRootControl getContentTypeRootControl() {
		return contentTypeRootControl;
	}
	public void setContentTypeRootControl(ContentTypeRootControl contentTypeRootControl) {
		this.contentTypeRootControl = contentTypeRootControl;
	}
	@XmlElement
	public LinkedHashSet<DataSourceControl> getDataSourceControl() {
		return dataSourceControl;
	}
	public void setDataSourceControl(LinkedHashSet<DataSourceControl> dataSourceControls) {
		this.dataSourceControl = dataSourceControls;
	}
	@Override
	@XmlElement
	public Source getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Source message) {
		super.setMessage(message);
	}
	
	public SourceControl() {
		super();
		dataSourceControl = new LinkedHashSet<DataSourceControl>();
	}
	public SourceControl(Source message) {
		super(message);
		dataSourceControl = new LinkedHashSet<DataSourceControl>();
	}
	public SourceControl(Source message, Control parent) {
		super(message, parent);
		dataSourceControl = new LinkedHashSet<DataSourceControl>();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				// cast source XML message
				Source source = Source.class.cast(e.getValue());
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
				contentTypeRootControl = ContentTypeRootControl.class.cast(e.getSource());
				// save control.
				addChild(contentTypeRootControl);
			} else if (e.getSource() instanceof DataSourceControl) {			
				DataSourceControl dataSourceControl = DataSourceControl.class.cast(e.getSource());
				// save control.
				getDataSourceControl().add(dataSourceControl);
				addChild(dataSourceControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				Source source = (Source) e.getValue();
				// check if control has child XML controls
				if (hasChildren()) {
					// iterate through child controls
					while (hasNext()) {
						// get next XML child control
						Control control = (Control) next();
						// check if child control is expected
						if (control instanceof ContentTypeRootControl ? 
								contentTypeRootControl.equals(control)
								: false) {
							// load control
							control.load();
						} else if (control instanceof DataSourceControl ? 
								dataSourceControl.contains(control) 
								: false) {
							// look for matching dataSource control's XML message.
							for (DataSource dataSource : source.getDataSource()) {
								// by UUID.
								if (control.getName().equals(dataSource.getName())) {
									// load the XML message control.
									control.load();
									break;
								}
							}
						}
					}
					// set control ready to be iterated again.
					reset();
				} else {
					throw new Error("SourceControl.OnEventReceived: Control must have XML message child controls.");
				}
				// look for data source control
			} else if (e.getSource() instanceof DataSourceControl) {
				// check if control has been initialitzed in this control
				if (getChildren().contains(e.getSource())) {
					// check if data source's data exists
					if(get(Data.DATA_SOURCE) == null) {
						// instance new set
						Set<Message> set = new LinkedHashSet<Message>();
						// add first data source value to set
						set.add((DataSource) e.getValue());
						// set data
						put(Data.DATA_SOURCE, set);
					} else {
						// add message to data
						Object set = get(Data.DATA_SOURCE);
						((Set<Message>) set).add((DataSource) e.getValue());
					}
				}
				// look for content type root control
			} else if (e.getSource() instanceof ContentTypeRootControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE_ROOT, (ContentTypeRoot) e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}