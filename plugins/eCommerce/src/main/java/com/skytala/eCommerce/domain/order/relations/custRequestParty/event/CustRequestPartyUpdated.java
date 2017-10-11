package com.skytala.eCommerce.domain.order.relations.custRequestParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestParty.model.CustRequestParty;
public class CustRequestPartyUpdated implements Event{

	private boolean success;

	public CustRequestPartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
