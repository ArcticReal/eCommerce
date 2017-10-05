package com.skytala.eCommerce.domain.termType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.termType.model.TermType;
public class TermTypeUpdated implements Event{

	private boolean success;

	public TermTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
