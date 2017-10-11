package com.skytala.eCommerce.domain.product.relations.container.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.Container;
public class ContainerDeleted implements Event{

	private boolean success;

	public ContainerDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
