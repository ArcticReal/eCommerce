package com.skytala.eCommerce.domain.product.relations.container.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
public class ContainerGeoPointAdded implements Event{

	private ContainerGeoPoint addedContainerGeoPoint;
	private boolean success;

	public ContainerGeoPointAdded(ContainerGeoPoint addedContainerGeoPoint, boolean success){
		this.addedContainerGeoPoint = addedContainerGeoPoint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContainerGeoPoint getAddedContainerGeoPoint() {
		return addedContainerGeoPoint;
	}

}
