package com.skytala.eCommerce.domain.returnAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnAdjustment.model.ReturnAdjustment;
public class ReturnAdjustmentDeleted implements Event{

	private boolean success;

	public ReturnAdjustmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
