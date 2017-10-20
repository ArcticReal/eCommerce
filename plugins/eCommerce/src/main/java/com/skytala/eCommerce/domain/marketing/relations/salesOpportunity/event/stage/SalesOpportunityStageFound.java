package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;
public class SalesOpportunityStageFound implements Event{

	private List<SalesOpportunityStage> salesOpportunityStages;

	public SalesOpportunityStageFound(List<SalesOpportunityStage> salesOpportunityStages) {
		this.salesOpportunityStages = salesOpportunityStages;
	}

	public List<SalesOpportunityStage> getSalesOpportunityStages()	{
		return salesOpportunityStages;
	}

}
