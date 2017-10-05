package com.skytala.eCommerce.domain.containerType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.containerType.model.ContainerType;
public class ContainerTypeDeleted implements Event{

	private boolean success;

	public ContainerTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
