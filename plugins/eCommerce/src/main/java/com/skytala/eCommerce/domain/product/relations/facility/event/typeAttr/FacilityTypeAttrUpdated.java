package com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;
public class FacilityTypeAttrUpdated implements Event{

	private boolean success;

	public FacilityTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
