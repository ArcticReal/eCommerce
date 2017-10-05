package com.skytala.eCommerce.domain.container.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.container.model.Container;
public class ContainerUpdated implements Event{

	private boolean success;

	public ContainerUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
