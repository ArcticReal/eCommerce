package com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;
public class DataResourceMetaDataDeleted implements Event{

	private boolean success;

	public DataResourceMetaDataDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
