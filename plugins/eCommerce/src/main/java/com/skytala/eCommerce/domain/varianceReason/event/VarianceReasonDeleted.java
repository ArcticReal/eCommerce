package com.skytala.eCommerce.domain.varianceReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.varianceReason.model.VarianceReason;
public class VarianceReasonDeleted implements Event{

	private boolean success;

	public VarianceReasonDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
