package com.skytala.eCommerce.domain.product.relations.container.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.Container;
public class ContainerFound implements Event{

	private List<Container> containers;

	public ContainerFound(List<Container> containers) {
		this.containers = containers;
	}

	public List<Container> getContainers()	{
		return containers;
	}

}
