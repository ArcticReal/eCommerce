package com.skytala.eCommerce.domain.party.relations.partyDataSource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;
public class PartyDataSourceUpdated implements Event{

	private boolean success;

	public PartyDataSourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
