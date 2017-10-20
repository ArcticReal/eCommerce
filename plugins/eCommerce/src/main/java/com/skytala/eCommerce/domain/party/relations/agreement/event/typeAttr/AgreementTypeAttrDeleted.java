package com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
public class AgreementTypeAttrDeleted implements Event{

	private boolean success;

	public AgreementTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
