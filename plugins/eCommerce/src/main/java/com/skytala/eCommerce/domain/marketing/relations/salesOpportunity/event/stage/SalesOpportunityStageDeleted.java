package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;
public class SalesOpportunityStageDeleted implements Event{

	private boolean success;

	public SalesOpportunityStageDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
