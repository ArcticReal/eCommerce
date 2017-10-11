package com.skytala.eCommerce.domain.product.relations.goodIdentification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
public class GoodIdentificationDeleted implements Event{

	private boolean success;

	public GoodIdentificationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
