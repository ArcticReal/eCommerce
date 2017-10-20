package com.skytala.eCommerce.domain.product.relations.container.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.type.ContainerType;
public class ContainerTypeDeleted implements Event{

	private boolean success;

	public ContainerTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
