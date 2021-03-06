package com.skytala.eCommerce.domain.party.relations.agreement.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
public class AgreementContentAdded implements Event{

	private AgreementContent addedAgreementContent;
	private boolean success;

	public AgreementContentAdded(AgreementContent addedAgreementContent, boolean success){
		this.addedAgreementContent = addedAgreementContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementContent getAddedAgreementContent() {
		return addedAgreementContent;
	}

}
