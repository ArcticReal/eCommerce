package com.skytala.eCommerce.domain.content.relations.content.event.metaData;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;
public class ContentMetaDataDeleted implements Event{

	private boolean success;

	public ContentMetaDataDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
