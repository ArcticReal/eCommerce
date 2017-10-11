package com.skytala.eCommerce.domain.product.relations.containerType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.containerType.model.ContainerType;
public class ContainerTypeFound implements Event{

	private List<ContainerType> containerTypes;

	public ContainerTypeFound(List<ContainerType> containerTypes) {
		this.containerTypes = containerTypes;
	}

	public List<ContainerType> getContainerTypes()	{
		return containerTypes;
	}

}
