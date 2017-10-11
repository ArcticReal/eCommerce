package com.skytala.eCommerce.domain.order.relations.quoteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteRole.model.QuoteRole;
public class QuoteRoleAdded implements Event{

	private QuoteRole addedQuoteRole;
	private boolean success;

	public QuoteRoleAdded(QuoteRole addedQuoteRole, boolean success){
		this.addedQuoteRole = addedQuoteRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteRole getAddedQuoteRole() {
		return addedQuoteRole;
	}

}
