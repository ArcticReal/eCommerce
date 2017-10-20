package com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;
public class AgreementWorkEffortApplicAdded implements Event{

	private AgreementWorkEffortApplic addedAgreementWorkEffortApplic;
	private boolean success;

	public AgreementWorkEffortApplicAdded(AgreementWorkEffortApplic addedAgreementWorkEffortApplic, boolean success){
		this.addedAgreementWorkEffortApplic = addedAgreementWorkEffortApplic;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementWorkEffortApplic getAddedAgreementWorkEffortApplic() {
		return addedAgreementWorkEffortApplic;
	}

}
