package com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;
public class AgreementWorkEffortApplicFound implements Event{

	private List<AgreementWorkEffortApplic> agreementWorkEffortApplics;

	public AgreementWorkEffortApplicFound(List<AgreementWorkEffortApplic> agreementWorkEffortApplics) {
		this.agreementWorkEffortApplics = agreementWorkEffortApplics;
	}

	public List<AgreementWorkEffortApplic> getAgreementWorkEffortApplics()	{
		return agreementWorkEffortApplics;
	}

}
