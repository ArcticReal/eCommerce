package com.skytala.eCommerce.domain.content.relations.electronicText.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
public class ElectronicTextUpdated implements Event{

	private boolean success;

	public ElectronicTextUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
