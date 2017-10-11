package com.skytala.eCommerce.domain.product.relations.marketInterest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
public class MarketInterestUpdated implements Event{

	private boolean success;

	public MarketInterestUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
