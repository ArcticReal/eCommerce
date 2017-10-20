package com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
public class ReturnAdjustmentTypeUpdated implements Event{

	private boolean success;

	public ReturnAdjustmentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
