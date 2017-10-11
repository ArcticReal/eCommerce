package com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.model.AgreementTypeAttr;
public class AgreementTypeAttrUpdated implements Event{

	private boolean success;

	public AgreementTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
