package com.skytala.eCommerce.domain.product.relations.facilityContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;
public class FacilityContentAdded implements Event{

	private FacilityContent addedFacilityContent;
	private boolean success;

	public FacilityContentAdded(FacilityContent addedFacilityContent, boolean success){
		this.addedFacilityContent = addedFacilityContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityContent getAddedFacilityContent() {
		return addedFacilityContent;
	}

}
