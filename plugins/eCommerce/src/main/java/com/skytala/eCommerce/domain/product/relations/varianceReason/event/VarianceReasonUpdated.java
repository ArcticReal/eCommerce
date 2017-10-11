package com.skytala.eCommerce.domain.product.relations.varianceReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.varianceReason.model.VarianceReason;
public class VarianceReasonUpdated implements Event{

	private boolean success;

	public VarianceReasonUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
