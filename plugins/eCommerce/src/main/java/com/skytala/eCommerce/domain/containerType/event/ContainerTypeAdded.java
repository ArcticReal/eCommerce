package com.skytala.eCommerce.domain.containerType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.containerType.model.ContainerType;
public class ContainerTypeAdded implements Event{

	private ContainerType addedContainerType;
	private boolean success;

	public ContainerTypeAdded(ContainerType addedContainerType, boolean success){
		this.addedContainerType = addedContainerType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContainerType getAddedContainerType() {
		return addedContainerType;
	}

}
