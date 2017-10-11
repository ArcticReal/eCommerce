package com.skytala.eCommerce.domain.product.relations.containerType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.containerType.model.ContainerType;
public class ContainerTypeDeleted implements Event{

	private boolean success;

	public ContainerTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
