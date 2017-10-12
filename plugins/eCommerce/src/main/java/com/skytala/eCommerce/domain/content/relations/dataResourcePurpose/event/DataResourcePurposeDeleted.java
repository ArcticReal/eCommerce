package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;
public class DataResourcePurposeDeleted implements Event{

	private boolean success;

	public DataResourcePurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
