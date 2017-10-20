package com.skytala.eCommerce.domain.party.relations.agreement.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
public class AgreementContentUpdated implements Event{

	private boolean success;

	public AgreementContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
