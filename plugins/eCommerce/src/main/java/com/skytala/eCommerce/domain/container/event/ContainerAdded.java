package com.skytala.eCommerce.domain.container.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.container.model.Container;
public class ContainerAdded implements Event{

	private Container addedContainer;
	private boolean success;

	public ContainerAdded(Container addedContainer, boolean success){
		this.addedContainer = addedContainer;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Container getAddedContainer() {
		return addedContainer;
	}

}
