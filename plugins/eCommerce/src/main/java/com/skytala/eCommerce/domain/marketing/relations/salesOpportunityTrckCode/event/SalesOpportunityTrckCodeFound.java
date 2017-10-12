package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;
public class SalesOpportunityTrckCodeFound implements Event{

	private List<SalesOpportunityTrckCode> salesOpportunityTrckCodes;

	public SalesOpportunityTrckCodeFound(List<SalesOpportunityTrckCode> salesOpportunityTrckCodes) {
		this.salesOpportunityTrckCodes = salesOpportunityTrckCodes;
	}

	public List<SalesOpportunityTrckCode> getSalesOpportunityTrckCodes()	{
		return salesOpportunityTrckCodes;
	}

}
