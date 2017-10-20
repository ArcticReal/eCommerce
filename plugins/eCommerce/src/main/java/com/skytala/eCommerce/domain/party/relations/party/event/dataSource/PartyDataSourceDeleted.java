package com.skytala.eCommerce.domain.party.relations.party.event.dataSource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.dataSource.PartyDataSource;
public class PartyDataSourceDeleted implements Event{

	private boolean success;

	public PartyDataSourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
