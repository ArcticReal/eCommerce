package com.skytala.eCommerce.domain.party.relations.needType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;
public class NeedTypeUpdated implements Event{

	private boolean success;

	public NeedTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
