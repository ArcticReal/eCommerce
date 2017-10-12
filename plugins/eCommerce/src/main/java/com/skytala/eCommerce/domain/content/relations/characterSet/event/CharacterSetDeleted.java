package com.skytala.eCommerce.domain.content.relations.characterSet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.characterSet.model.CharacterSet;
public class CharacterSetDeleted implements Event{

	private boolean success;

	public CharacterSetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
