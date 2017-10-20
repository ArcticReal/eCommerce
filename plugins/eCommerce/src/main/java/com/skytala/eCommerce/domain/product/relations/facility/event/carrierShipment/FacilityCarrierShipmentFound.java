package com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;
public class FacilityCarrierShipmentFound implements Event{

	private List<FacilityCarrierShipment> facilityCarrierShipments;

	public FacilityCarrierShipmentFound(List<FacilityCarrierShipment> facilityCarrierShipments) {
		this.facilityCarrierShipments = facilityCarrierShipments;
	}

	public List<FacilityCarrierShipment> getFacilityCarrierShipments()	{
		return facilityCarrierShipments;
	}

}
