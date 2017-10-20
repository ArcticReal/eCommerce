package com.skytala.eCommerce.domain.order.relations.custRequest.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;
public class CustRequestPartyAdded implements Event{

	private CustRequestParty addedCustRequestParty;
	private boolean success;

	public CustRequestPartyAdded(CustRequestParty addedCustRequestParty, boolean success){
		this.addedCustRequestParty = addedCustRequestParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestParty getAddedCustRequestParty() {
		return addedCustRequestParty;
	}

}
