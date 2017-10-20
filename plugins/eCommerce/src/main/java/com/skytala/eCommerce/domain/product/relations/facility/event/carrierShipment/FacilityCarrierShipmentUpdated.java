package com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;
public class FacilityCarrierShipmentUpdated implements Event{

	private boolean success;

	public FacilityCarrierShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}