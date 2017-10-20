package com.skytala.eCommerce.domain.product.relations.container.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
public class ContainerGeoPointUpdated implements Event{

	private boolean success;

	public ContainerGeoPointUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
