package com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;
public class GoodIdentificationTypeUpdated implements Event{

	private boolean success;

	public GoodIdentificationTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
