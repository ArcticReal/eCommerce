package com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;
public class GoodIdentificationTypeDeleted implements Event{

	private boolean success;

	public GoodIdentificationTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
