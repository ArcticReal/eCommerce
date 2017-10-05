package com.skytala.eCommerce.domain.agreementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreementType.model.AgreementType;
public class AgreementTypeAdded implements Event{

	private AgreementType addedAgreementType;
	private boolean success;

	public AgreementTypeAdded(AgreementType addedAgreementType, boolean success){
		this.addedAgreementType = addedAgreementType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementType getAddedAgreementType() {
		return addedAgreementType;
	}

}
