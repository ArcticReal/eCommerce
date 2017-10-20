package com.skytala.eCommerce.domain.party.relations.party.event.dataSource;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
public class PartyDataSourceFound implements Event{

	private List<PartyDataSource> partyDataSources;

	public PartyDataSourceFound(List<PartyDataSource> partyDataSources) {
		this.partyDataSources = partyDataSources;
	}

	public List<PartyDataSource> getPartyDataSources()	{
		return partyDataSources;
	}

}
