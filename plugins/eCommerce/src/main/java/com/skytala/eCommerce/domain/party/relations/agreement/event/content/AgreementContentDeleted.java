package com.skytala.eCommerce.domain.party.relations.agreement.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
public class AgreementContentDeleted implements Event{

	private boolean success;

	public AgreementContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
