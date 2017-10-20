package com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
public class AgreementItemTypeAttrUpdated implements Event{

	private boolean success;

	public AgreementItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
