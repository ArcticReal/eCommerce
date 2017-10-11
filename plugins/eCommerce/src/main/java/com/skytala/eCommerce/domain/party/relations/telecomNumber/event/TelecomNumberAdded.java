package com.skytala.eCommerce.domain.party.relations.telecomNumber.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
public class TelecomNumberAdded implements Event{

	private TelecomNumber addedTelecomNumber;
	private boolean success;

	public TelecomNumberAdded(TelecomNumber addedTelecomNumber, boolean success){
		this.addedTelecomNumber = addedTelecomNumber;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TelecomNumber getAddedTelecomNumber() {
		return addedTelecomNumber;
	}

}
