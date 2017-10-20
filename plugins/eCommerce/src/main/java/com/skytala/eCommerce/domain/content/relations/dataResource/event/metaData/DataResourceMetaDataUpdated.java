package com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;
public class DataResourceMetaDataUpdated implements Event{

	private boolean success;

	public DataResourceMetaDataUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
