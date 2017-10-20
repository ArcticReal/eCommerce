package com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;
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
