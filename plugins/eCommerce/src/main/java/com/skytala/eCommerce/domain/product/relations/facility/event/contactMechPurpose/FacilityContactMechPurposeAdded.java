package com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.contactMechPurpose.FacilityContactMechPurpose;
public class FacilityContactMechPurposeAdded implements Event{

	private FacilityContactMechPurpose addedFacilityContactMechPurpose;
	private boolean success;

	public FacilityContactMechPurposeAdded(FacilityContactMechPurpose addedFacilityContactMechPurpose, boolean success){
		this.addedFacilityContactMechPurpose = addedFacilityContactMechPurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityContactMechPurpose getAddedFacilityContactMechPurpose() {
		return addedFacilityContactMechPurpose;
	}

}
