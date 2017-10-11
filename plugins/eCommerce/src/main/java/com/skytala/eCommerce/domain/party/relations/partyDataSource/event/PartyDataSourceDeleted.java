package com.skytala.eCommerce.domain.party.relations.partyDataSource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;
public class PartyDataSourceDeleted implements Event{

	private boolean success;

	public PartyDataSourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
