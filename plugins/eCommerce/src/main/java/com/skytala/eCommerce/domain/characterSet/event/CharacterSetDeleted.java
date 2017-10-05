package com.skytala.eCommerce.domain.characterSet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
public class CharacterSetDeleted implements Event{

	private boolean success;

	public CharacterSetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
