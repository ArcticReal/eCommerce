package com.skytala.eCommerce.domain.product.relations.lot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;
public class LotUpdated implements Event{

	private boolean success;

	public LotUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
