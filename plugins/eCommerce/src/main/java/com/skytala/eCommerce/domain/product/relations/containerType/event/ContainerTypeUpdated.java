package com.skytala.eCommerce.domain.product.relations.containerType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.containerType.model.ContainerType;
public class ContainerTypeUpdated implements Event{

	private boolean success;

	public ContainerTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
