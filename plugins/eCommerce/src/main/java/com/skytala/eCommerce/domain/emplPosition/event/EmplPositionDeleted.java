package com.skytala.eCommerce.domain.emplPosition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.emplPosition.model.EmplPosition;
public class EmplPositionDeleted implements Event{

	private boolean success;

	public EmplPositionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
