package com.skytala.eCommerce.domain.returnHeaderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnHeaderType.model.ReturnHeaderType;
public class ReturnHeaderTypeUpdated implements Event{

	private boolean success;

	public ReturnHeaderTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
