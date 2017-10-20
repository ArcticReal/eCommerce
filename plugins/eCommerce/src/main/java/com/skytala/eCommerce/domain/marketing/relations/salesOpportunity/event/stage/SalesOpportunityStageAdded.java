package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;
public class SalesOpportunityStageAdded implements Event{

	private SalesOpportunityStage addedSalesOpportunityStage;
	private boolean success;

	public SalesOpportunityStageAdded(SalesOpportunityStage addedSalesOpportunityStage, boolean success){
		this.addedSalesOpportunityStage = addedSalesOpportunityStage;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityStage getAddedSalesOpportunityStage() {
		return addedSalesOpportunityStage;
	}

}
