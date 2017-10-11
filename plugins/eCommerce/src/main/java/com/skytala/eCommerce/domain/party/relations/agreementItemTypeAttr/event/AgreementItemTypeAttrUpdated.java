package com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.model.AgreementItemTypeAttr;
public class AgreementItemTypeAttrUpdated implements Event{

	private boolean success;

	public AgreementItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
