package com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.model.AgreementTypeAttr;
public class AgreementTypeAttrDeleted implements Event{

	private boolean success;

	public AgreementTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
