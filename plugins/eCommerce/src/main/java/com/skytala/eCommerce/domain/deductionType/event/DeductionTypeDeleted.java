package com.skytala.eCommerce.domain.deductionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deductionType.model.DeductionType;
public class DeductionTypeDeleted implements Event{

	private boolean success;

	public DeductionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
