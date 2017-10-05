package com.skytala.eCommerce.domain.returnAdjustmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnAdjustmentType.model.ReturnAdjustmentType;
public class ReturnAdjustmentTypeUpdated implements Event{

	private boolean success;

	public ReturnAdjustmentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
