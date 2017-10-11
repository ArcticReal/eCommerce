package com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.model.AgreementPromoAppl;
public class AgreementPromoApplUpdated implements Event{

	private boolean success;

	public AgreementPromoApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
