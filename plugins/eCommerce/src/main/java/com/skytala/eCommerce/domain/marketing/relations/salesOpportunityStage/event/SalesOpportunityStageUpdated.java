package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.model.SalesOpportunityStage;
public class SalesOpportunityStageUpdated implements Event{

	private boolean success;

	public SalesOpportunityStageUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
