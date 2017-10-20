package com.skytala.eCommerce.domain.order.relations.quote.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.term.QuoteTerm;
public class QuoteTermAdded implements Event{

	private QuoteTerm addedQuoteTerm;
	private boolean success;

	public QuoteTermAdded(QuoteTerm addedQuoteTerm, boolean success){
		this.addedQuoteTerm = addedQuoteTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteTerm getAddedQuoteTerm() {
		return addedQuoteTerm;
	}

}
