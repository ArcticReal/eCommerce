package com.skytala.eCommerce.domain.termType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.termType.model.TermType;
public class TermTypeDeleted implements Event{

	private boolean success;

	public TermTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
