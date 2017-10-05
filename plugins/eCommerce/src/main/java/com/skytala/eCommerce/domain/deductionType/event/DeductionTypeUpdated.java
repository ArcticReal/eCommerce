package com.skytala.eCommerce.domain.deductionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deductionType.model.DeductionType;
public class DeductionTypeUpdated implements Event{

	private boolean success;

	public DeductionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
