package com.skytala.eCommerce.domain.party.relations.telecomNumber.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
public class TelecomNumberFound implements Event{

	private List<TelecomNumber> telecomNumbers;

	public TelecomNumberFound(List<TelecomNumber> telecomNumbers) {
		this.telecomNumbers = telecomNumbers;
	}

	public List<TelecomNumber> getTelecomNumbers()	{
		return telecomNumbers;
	}

}
