package com.skytala.eCommerce.domain.product.relations.container.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.container.model.geoPoint.ContainerGeoPoint;
public class ContainerGeoPointDeleted implements Event{

	private boolean success;

	public ContainerGeoPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
