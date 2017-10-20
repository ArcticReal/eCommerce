package com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;
public class FacilityTypeAttrDeleted implements Event{

	private boolean success;

	public FacilityTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
