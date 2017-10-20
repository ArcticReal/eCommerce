package com.skytala.eCommerce.domain.product.relations.container.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.type.ContainerType;
public class ContainerTypeUpdated implements Event{

	private boolean success;

	public ContainerTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
