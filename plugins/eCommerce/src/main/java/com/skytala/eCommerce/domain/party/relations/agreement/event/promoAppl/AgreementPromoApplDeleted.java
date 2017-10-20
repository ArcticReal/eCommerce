package com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;
public class AgreementPromoApplDeleted implements Event{

	private boolean success;

	public AgreementPromoApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
