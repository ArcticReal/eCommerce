package com.skytala.eCommerce.domain.product.relations.containerGeoPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.model.ContainerGeoPoint;
public class ContainerGeoPointDeleted implements Event{

	private boolean success;

	public ContainerGeoPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
