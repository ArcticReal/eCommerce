package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupType.GlAccountGroupType;
public class GlAccountGroupTypeUpdated implements Event{

	private boolean success;

	public GlAccountGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}