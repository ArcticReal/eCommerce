package com.skytala.eCommerce.domain.order.relations.quoteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteRole.model.QuoteRole;
public class QuoteRoleUpdated implements Event{

	private boolean success;

	public QuoteRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
