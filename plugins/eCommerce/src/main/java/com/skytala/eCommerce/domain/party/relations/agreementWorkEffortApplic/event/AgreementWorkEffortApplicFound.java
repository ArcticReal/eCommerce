package com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.model.AgreementWorkEffortApplic;
public class AgreementWorkEffortApplicFound implements Event{

	private List<AgreementWorkEffortApplic> agreementWorkEffortApplics;

	public AgreementWorkEffortApplicFound(List<AgreementWorkEffortApplic> agreementWorkEffortApplics) {
		this.agreementWorkEffortApplics = agreementWorkEffortApplics;
	}

	public List<AgreementWorkEffortApplic> getAgreementWorkEffortApplics()	{
		return agreementWorkEffortApplics;
	}

}
