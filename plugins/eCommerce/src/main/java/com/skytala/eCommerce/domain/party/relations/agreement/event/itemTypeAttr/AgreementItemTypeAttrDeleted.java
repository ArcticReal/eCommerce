package com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
public class AgreementItemTypeAttrDeleted implements Event{

	private boolean success;

	public AgreementItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
