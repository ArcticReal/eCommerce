package com.skytala.eCommerce.domain.product.relations.goodIdentification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
public class GoodIdentificationUpdated implements Event{

	private boolean success;

	public GoodIdentificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
