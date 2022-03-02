package org.httprobot.configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ParentListener;
import org.httprobot.Data;
import org.httprobot.MappingParent;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.content.ContentTypeRootControl;
import org.httprobot.content.ContentTypeRootParent;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.datatype.DataSource;
import org.httprobot.datatype.DataSourceControl;
import org.httprobot.datatype.DataSourceParent;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

@XmlRootElement
public final class SourceParent
	extends MappingParent<DataSource, DocumentLibrary, SourceControl> {

	/**
	 * 634599347187276700L
	 */
	private static final long serialVersionUID = 634599347187276700L;

	ContentTypeRootParent contentTypeRootParent;
	Map<DataSource, DataSourceParent> dataSouceParents;

	@Override
	@XmlElement
	public SourceControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(SourceControl control) {
		super.setControl(control);
	}
	
	public SourceParent() {
		super();
		dataSouceParents = new LinkedHashMap<DataSource, DataSourceParent>();
		setTemplateLibrary(new TemplateLibrary());
		setConstants(new LinkedHashMap<String,String>());
	}
	public SourceParent(Source message, ParentListener parent) {
		super(message, SourceControl.class, parent);
		dataSouceParents = new LinkedHashMap<DataSource, DataSourceParent>();
		setTemplateLibrary(new TemplateLibrary());
		setConstants(new LinkedHashMap<String,String>());
		setContentTypeRoot(message.getContentTypeRoot());
	}
	@Override
	public DocumentLibrary put(DataSource key, DocumentLibrary value) {
		setKey(key);
		setValue(value);
		DataSourceParent dataSourceManager = dataSouceParents.get(key);
		dataSourceManager.setDocumentLibrary(value);
		
		return super.put(key, value);
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRootControl) {
				ContentTypeRoot message = ContentTypeRootControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTENT_TYPE_ROOT).equals(message)) {
					contentTypeRootParent = new ContentTypeRootParent(message, this);
					addChildManager(contentTypeRootParent);
				}
			} else if(e.getSource() instanceof DataSourceControl) {
				DataSource message = DataSourceControl.class.cast(e.getSource()).getMessage();
				@SuppressWarnings("unchecked")
				Set<Message> set = (Set<Message>) getControl().get(Data.DATA_SOURCE);
				if(set.contains(message)) {
					DataSourceParent dataSourceManager = new DataSourceParent(message, this);
					dataSouceParents.put(message, dataSourceManager);
					addChildManager(dataSourceManager);
				}
			}
		default:
			break;
		}
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(contentTypeRootParent)) {
				contentTypeRootParent.setValue(getTemplateLibrary());
			}
			break;
		case FINISHED:
			if(e.getSource().equals(contentTypeRootParent)) {
				if(contentTypeRootParent.getKey().equals(getControl().get(Data.CONTENT_TYPE_ROOT))) {
					getTemplateLibrary().putAll(contentTypeRootParent.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}