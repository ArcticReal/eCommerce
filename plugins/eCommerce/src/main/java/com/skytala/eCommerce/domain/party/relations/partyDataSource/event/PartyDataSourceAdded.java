package com.skytala.eCommerce.domain.party.relations.partyDataSource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;
public class PartyDataSourceAdded implements Event{

	private PartyDataSource addedPartyDataSource;
	private boolean success;

	public PartyDataSourceAdded(PartyDataSource addedPartyDataSource, boolean success){
		this.addedPartyDataSource = addedPartyDataSource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyDataSource getAddedPartyDataSource() {
		return addedPartyDataSource;
	}

}
