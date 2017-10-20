package com.skytala.eCommerce.domain.order.relations.quote.event.workEffort;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.workEffort.QuoteWorkEffort;
public class QuoteWorkEffortFound implements Event{

	private List<QuoteWorkEffort> quoteWorkEfforts;

	public QuoteWorkEffortFound(List<QuoteWorkEffort> quoteWorkEfforts) {
		this.quoteWorkEfforts = quoteWorkEfforts;
	}

	public List<QuoteWorkEffort> getQuoteWorkEfforts()	{
		return quoteWorkEfforts;
	}

}
