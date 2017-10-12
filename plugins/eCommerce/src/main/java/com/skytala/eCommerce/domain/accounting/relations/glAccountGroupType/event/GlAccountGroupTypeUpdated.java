package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupType.model.GlAccountGroupType;
public class GlAccountGroupTypeUpdated implements Event{

	private boolean success;

	public GlAccountGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
