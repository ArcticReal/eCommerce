package com.skytala.eCommerce.domain.product.relations.facilityContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;
public class FacilityContactMechDeleted implements Event{

	private boolean success;

	public FacilityContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
