package com.skytala.eCommerce.domain.order.relations.returnAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.ReturnAdjustment;
public class ReturnAdjustmentUpdated implements Event{

	private boolean success;

	public ReturnAdjustmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
