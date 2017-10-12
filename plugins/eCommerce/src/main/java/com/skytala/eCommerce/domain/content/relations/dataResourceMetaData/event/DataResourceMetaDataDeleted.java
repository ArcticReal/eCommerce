package com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.model.DataResourceMetaData;
public class DataResourceMetaDataDeleted implements Event{

	private boolean success;

	public DataResourceMetaDataDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
