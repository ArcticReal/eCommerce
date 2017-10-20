package com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
public class AgreementTypeAttrUpdated implements Event{

	private boolean success;

	public AgreementTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
