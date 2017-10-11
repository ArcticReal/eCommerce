package com.skytala.eCommerce.domain.party.relations.commContentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;
public class CommContentAssocTypeDeleted implements Event{

	private boolean success;

	public CommContentAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
