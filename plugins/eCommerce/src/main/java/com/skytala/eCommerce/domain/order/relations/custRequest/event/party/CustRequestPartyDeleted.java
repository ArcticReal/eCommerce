package com.skytala.eCommerce.domain.order.relations.custRequest.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;
public class CustRequestPartyDeleted implements Event{

	private boolean success;

	public CustRequestPartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
