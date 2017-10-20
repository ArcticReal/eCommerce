package com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;
public class AgreementPromoApplUpdated implements Event{

	private boolean success;

	public AgreementPromoApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
