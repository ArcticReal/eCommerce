package com.skytala.eCommerce.domain.party.relations.partyDataSource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;
public class PartyDataSourceFound implements Event{

	private List<PartyDataSource> partyDataSources;

	public PartyDataSourceFound(List<PartyDataSource> partyDataSources) {
		this.partyDataSources = partyDataSources;
	}

	public List<PartyDataSource> getPartyDataSources()	{
		return partyDataSources;
	}

}
