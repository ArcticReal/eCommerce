package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;
public class FacilityContactMechPurposeFound implements Event{

	private List<FacilityContactMechPurpose> facilityContactMechPurposes;

	public FacilityContactMechPurposeFound(List<FacilityContactMechPurpose> facilityContactMechPurposes) {
		this.facilityContactMechPurposes = facilityContactMechPurposes;
	}

	public List<FacilityContactMechPurpose> getFacilityContactMechPurposes()	{
		return facilityContactMechPurposes;
	}

}
