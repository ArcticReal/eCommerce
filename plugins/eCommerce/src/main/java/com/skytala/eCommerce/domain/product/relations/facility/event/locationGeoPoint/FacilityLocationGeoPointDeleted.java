package com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;
public class FacilityLocationGeoPointDeleted implements Event{

	private boolean success;

	public FacilityLocationGeoPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
