package com.skytala.eCommerce.domain.commContentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.commContentAssocType.model.CommContentAssocType;
public class CommContentAssocTypeUpdated implements Event{

	private boolean success;

	public CommContentAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
