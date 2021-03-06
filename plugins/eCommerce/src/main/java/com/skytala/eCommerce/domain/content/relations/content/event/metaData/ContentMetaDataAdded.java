package com.skytala.eCommerce.domain.content.relations.content.event.metaData;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;
public class ContentMetaDataAdded implements Event{

	private ContentMetaData addedContentMetaData;
	private boolean success;

	public ContentMetaDataAdded(ContentMetaData addedContentMetaData, boolean success){
		this.addedContentMetaData = addedContentMetaData;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentMetaData getAddedContentMetaData() {
		return addedContentMetaData;
	}

}
