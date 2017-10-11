package com.skytala.eCommerce.domain.product.relations.facilityContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;
public class FacilityContactMechAdded implements Event{

	private FacilityContactMech addedFacilityContactMech;
	private boolean success;

	public FacilityContactMechAdded(FacilityContactMech addedFacilityContactMech, boolean success){
		this.addedFacilityContactMech = addedFacilityContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityContactMech getAddedFacilityContactMech() {
		return addedFacilityContactMech;
	}

}
