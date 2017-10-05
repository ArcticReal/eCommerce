package com.skytala.eCommerce.domain.characterSet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
public class CharacterSetUpdated implements Event{

	private boolean success;

	public CharacterSetUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
