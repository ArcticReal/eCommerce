package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;
public class FacilityTypeAttrAdded implements Event{

	private FacilityTypeAttr addedFacilityTypeAttr;
	private boolean success;

	public FacilityTypeAttrAdded(FacilityTypeAttr addedFacilityTypeAttr, boolean success){
		this.addedFacilityTypeAttr = addedFacilityTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityTypeAttr getAddedFacilityTypeAttr() {
		return addedFacilityTypeAttr;
	}

}
