package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
public class PartyTaxAuthInfoFound implements Event{

	private List<PartyTaxAuthInfo> partyTaxAuthInfos;

	public PartyTaxAuthInfoFound(List<PartyTaxAuthInfo> partyTaxAuthInfos) {
		this.partyTaxAuthInfos = partyTaxAuthInfos;
	}

	public List<PartyTaxAuthInfo> getPartyTaxAuthInfos()	{
		return partyTaxAuthInfos;
	}

}
