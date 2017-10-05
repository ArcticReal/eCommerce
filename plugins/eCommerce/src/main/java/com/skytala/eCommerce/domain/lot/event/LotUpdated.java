package com.skytala.eCommerce.domain.lot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.lot.model.Lot;
public class LotUpdated implements Event{

	private boolean success;

	public LotUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
