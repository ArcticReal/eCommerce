package com.skytala.eCommerce.domain.product.relations.marketInterest.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
public class MarketInterestFound implements Event{

	private List<MarketInterest> marketInterests;

	public MarketInterestFound(List<MarketInterest> marketInterests) {
		this.marketInterests = marketInterests;
	}

	public List<MarketInterest> getMarketInterests()	{
		return marketInterests;
	}

}
