package com.skytala.eCommerce.domain.order.relations.quoteAdjustment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteAdjustment.model.QuoteAdjustment;
public class QuoteAdjustmentFound implements Event{

	private List<QuoteAdjustment> quoteAdjustments;

	public QuoteAdjustmentFound(List<QuoteAdjustment> quoteAdjustments) {
		this.quoteAdjustments = quoteAdjustments;
	}

	public List<QuoteAdjustment> getQuoteAdjustments()	{
		return quoteAdjustments;
	}

}
