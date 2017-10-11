package com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.model.FacilityLocationGeoPoint;
public class FacilityLocationGeoPointUpdated implements Event{

	private boolean success;

	public FacilityLocationGeoPointUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
