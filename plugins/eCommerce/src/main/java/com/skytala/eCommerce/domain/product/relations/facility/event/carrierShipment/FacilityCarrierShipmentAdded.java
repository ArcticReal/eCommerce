package com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;
public class FacilityCarrierShipmentAdded implements Event{

	private FacilityCarrierShipment addedFacilityCarrierShipment;
	private boolean success;

	public FacilityCarrierShipmentAdded(FacilityCarrierShipment addedFacilityCarrierShipment, boolean success){
		this.addedFacilityCarrierShipment = addedFacilityCarrierShipment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityCarrierShipment getAddedFacilityCarrierShipment() {
		return addedFacilityCarrierShipment;
	}

}
