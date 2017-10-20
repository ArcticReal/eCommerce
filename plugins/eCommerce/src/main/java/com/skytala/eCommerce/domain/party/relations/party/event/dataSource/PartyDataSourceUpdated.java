package com.skytala.eCommerce.domain.party.relations.party.event.dataSource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
public class PartyDataSourceUpdated implements Event{

	private boolean success;

	public PartyDataSourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
