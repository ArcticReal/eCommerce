package com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.model.FacilityCarrierShipment;
public class FacilityCarrierShipmentUpdated implements Event{

	private boolean success;

	public FacilityCarrierShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
