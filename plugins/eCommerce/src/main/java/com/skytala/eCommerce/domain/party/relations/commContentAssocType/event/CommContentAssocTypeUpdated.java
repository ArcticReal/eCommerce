package com.skytala.eCommerce.domain.party.relations.commContentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;
public class CommContentAssocTypeUpdated implements Event{

	private boolean success;

	public CommContentAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
