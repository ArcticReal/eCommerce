package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;
public class FacilityTypeAttrUpdated implements Event{

	private boolean success;

	public FacilityTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
