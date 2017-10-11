package com.skytala.eCommerce.domain.order.relations.quoteTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteTerm.model.QuoteTerm;
public class QuoteTermUpdated implements Event{

	private boolean success;

	public QuoteTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
