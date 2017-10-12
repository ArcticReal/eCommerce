package com.skytala.eCommerce.domain.content.relations.otherDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.otherDataResource.model.OtherDataResource;
public class OtherDataResourceDeleted implements Event{

	private boolean success;

	public OtherDataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
