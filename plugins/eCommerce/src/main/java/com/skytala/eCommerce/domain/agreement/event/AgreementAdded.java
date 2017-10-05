package com.skytala.eCommerce.domain.agreement.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreement.model.Agreement;
public class AgreementAdded implements Event{

	private Agreement addedAgreement;
	private boolean success;

	public AgreementAdded(Agreement addedAgreement, boolean success){
		this.addedAgreement = addedAgreement;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Agreement getAddedAgreement() {
		return addedAgreement;
	}

}
