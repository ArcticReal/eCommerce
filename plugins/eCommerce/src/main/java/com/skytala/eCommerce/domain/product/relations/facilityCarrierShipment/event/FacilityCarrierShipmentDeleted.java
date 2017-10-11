package com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.model.FacilityCarrierShipment;
public class FacilityCarrierShipmentDeleted implements Event{

	private boolean success;

	public FacilityCarrierShipmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
