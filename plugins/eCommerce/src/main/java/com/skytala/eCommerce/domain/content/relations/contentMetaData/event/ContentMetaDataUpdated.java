package com.skytala.eCommerce.domain.content.relations.contentMetaData.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentMetaData.model.ContentMetaData;
public class ContentMetaDataUpdated implements Event{

	private boolean success;

	public ContentMetaDataUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
