package com.skytala.eCommerce.domain.product.relations.marketInterest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
public class MarketInterestAdded implements Event{

	private MarketInterest addedMarketInterest;
	private boolean success;

	public MarketInterestAdded(MarketInterest addedMarketInterest, boolean success){
		this.addedMarketInterest = addedMarketInterest;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MarketInterest getAddedMarketInterest() {
		return addedMarketInterest;
	}

}
