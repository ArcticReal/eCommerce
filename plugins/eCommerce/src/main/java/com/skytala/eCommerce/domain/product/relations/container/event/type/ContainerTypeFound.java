package com.skytala.eCommerce.domain.product.relations.container.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.type.ContainerType;
public class ContainerTypeFound implements Event{

	private List<ContainerType> containerTypes;

	public ContainerTypeFound(List<ContainerType> containerTypes) {
		this.containerTypes = containerTypes;
	}

	public List<ContainerType> getContainerTypes()	{
		return containerTypes;
	}

}
