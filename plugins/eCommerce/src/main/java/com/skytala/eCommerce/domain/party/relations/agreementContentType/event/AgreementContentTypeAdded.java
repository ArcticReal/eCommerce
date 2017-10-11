package com.skytala.eCommerce.domain.party.relations.agreementContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;
public class AgreementContentTypeAdded implements Event{

	private AgreementContentType addedAgreementContentType;
	private boolean success;

	public AgreementContentTypeAdded(AgreementContentType addedAgreementContentType, boolean success){
		this.addedAgreementContentType = addedAgreementContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementContentType getAddedAgreementContentType() {
		return addedAgreementContentType;
	}

}
