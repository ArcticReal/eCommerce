package com.skytala.eCommerce.domain.order.relations.custRequest.event.party;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;
public class CustRequestPartyFound implements Event{

	private List<CustRequestParty> custRequestPartys;

	public CustRequestPartyFound(List<CustRequestParty> custRequestPartys) {
		this.custRequestPartys = custRequestPartys;
	}

	public List<CustRequestParty> getCustRequestPartys()	{
		return custRequestPartys;
	}

}
