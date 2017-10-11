package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;
public class FacilityTypeAttrDeleted implements Event{

	private boolean success;

	public FacilityTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
