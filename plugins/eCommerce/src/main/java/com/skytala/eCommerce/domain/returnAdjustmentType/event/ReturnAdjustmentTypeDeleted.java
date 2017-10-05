package com.skytala.eCommerce.domain.returnAdjustmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnAdjustmentType.model.ReturnAdjustmentType;
public class ReturnAdjustmentTypeDeleted implements Event{

	private boolean success;

	public ReturnAdjustmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
