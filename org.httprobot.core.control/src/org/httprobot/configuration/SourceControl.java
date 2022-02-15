package org.httprobot.configuration;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Command;
import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRootControl;
import org.httprobot.datatype.DataSource;
import org.httprobot.datatype.DataSourceControl;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class SourceControl
	extends Control<Source> {

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
		if(dataSourceControl == null) {
			dataSourceControl = new LinkedHashSet<DataSourceControl>();
		}
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
	}
	public SourceControl(Source message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			// Cast Configuration XML message
			Source config = Source.class.cast(e.getMessage());

			if (config.getContentTypeRoot() != null) {
				// Initialize ContentTypeRoot message control.
				new ContentTypeRootControl(config.getContentTypeRoot(), this);
			} else {
				throw new Error("ConfigurationControl.OnControlInitialized: ContentTypeRoot XML message missing");
			}
			if (config.getDataSource() != null) {
				// Instance data source control message set
				dataSourceControl = new LinkedHashSet<DataSourceControl>();
				// For each dataSource in Configuration XML message
				for (DataSource dataSource : config.getDataSource()) {
					// This instance listens for it's OnCommandReceived event
					new DataSourceControl(dataSource, this);
				}
			} else {
				throw new Error("ConfigurationControl.OnControlInitialized: DataSource XML message missing");
			}
		} else if (e.getSource() instanceof ContentTypeRootControl) {
			contentTypeRootControl = ContentTypeRootControl.class.cast(e.getSource());
			// Store control.
			addChildControl(contentTypeRootControl);
		} else if (e.getSource() instanceof DataSourceControl) {			
			DataSourceControl dataSourceControl = DataSourceControl.class.cast(e.getSource());
			// Store control.
			getDataSourceControl().add(dataSourceControl);
			addChildControl(dataSourceControl);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			Source config = Source.class.cast(e.getMessage());

			// Check if control has child XML controls
			if (hasChildControls()) {
				// Iterate through child controls
				while (hasNext()) {
					// Get next XML child control
					ControlListener control = next();

					if (control instanceof ContentTypeRootControl ? 
							contentTypeRootControl.equals(control)
							: false) {

						// Set the ContentTypeRoot message to control.
						contentTypeRootControl.loadControl();
					} else if (control instanceof DataSourceControl ? 
							dataSourceControl.contains(control) : false) {

						DataSourceControl dataSourceControl = DataSourceControl.class.cast(control);

						// Look for matching dataSource control's XML message.
						for (DataSource dataSource : config.getDataSource()) {
							// by UUID.
							if (dataSourceControl.getUuid().equals(dataSource.getUuid())) {
								// Load the XML message control.
								dataSourceControl.loadControl();
								break;
							}
						}
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.CONFIGURATION_CONTROL_LOADED));
			} else {
				throw new Error(
						"ConfigurationControl.OnControlLoaded: Control must have XML message child controls.");
			}
		} else if (e.getSource() instanceof DataSourceControl) {
			if (getChildControls().contains(e.getSource())) {
				if(get(Data.DATA_SOURCE) == null) {
					Set<Message> set = new LinkedHashSet<Message>();
					set.add(e.getMessage());
					put(Data.DATA_SOURCE, set);
				} else {
					Object set = get(Data.DATA_SOURCE);
					((Set<Message>) set).add(e.getMessage());
				}
			}
		} else if (e.getSource() instanceof ContentTypeRootControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_ROOT, e.getMessage());
			}
		}
	}
}