package com.skytala.eCommerce.domain.content.relations.dataResource.event.other;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.other.OtherDataResource;
public class OtherDataResourceUpdated implements Event{

	private boolean success;

	public OtherDataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
