package com.skytala.eCommerce.domain.rateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.rateType.model.RateType;
public class RateTypeDeleted implements Event{

	private boolean success;

	public RateTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
