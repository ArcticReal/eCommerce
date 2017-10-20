package com.skytala.eCommerce.domain.product.relations.container.event.geoPoint;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
public class ContainerGeoPointFound implements Event{

	private List<ContainerGeoPoint> containerGeoPoints;

	public ContainerGeoPointFound(List<ContainerGeoPoint> containerGeoPoints) {
		this.containerGeoPoints = containerGeoPoints;
	}

	public List<ContainerGeoPoint> getContainerGeoPoints()	{
		return containerGeoPoints;
	}

}
