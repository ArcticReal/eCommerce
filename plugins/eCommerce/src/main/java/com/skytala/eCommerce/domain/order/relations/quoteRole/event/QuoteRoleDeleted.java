package com.skytala.eCommerce.domain.order.relations.quoteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteRole.model.QuoteRole;
public class QuoteRoleDeleted implements Event{

	private boolean success;

	public QuoteRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
