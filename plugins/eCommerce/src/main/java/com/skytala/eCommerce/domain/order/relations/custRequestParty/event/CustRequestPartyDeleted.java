package com.skytala.eCommerce.domain.order.relations.custRequestParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestParty.model.CustRequestParty;
public class CustRequestPartyDeleted implements Event{

	private boolean success;

	public CustRequestPartyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
