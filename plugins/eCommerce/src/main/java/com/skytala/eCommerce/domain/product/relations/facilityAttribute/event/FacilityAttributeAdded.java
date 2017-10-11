package com.skytala.eCommerce.domain.product.relations.facilityAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;
public class FacilityAttributeAdded implements Event{

	private FacilityAttribute addedFacilityAttribute;
	private boolean success;

	public FacilityAttributeAdded(FacilityAttribute addedFacilityAttribute, boolean success){
		this.addedFacilityAttribute = addedFacilityAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityAttribute getAddedFacilityAttribute() {
		return addedFacilityAttribute;
	}

}
