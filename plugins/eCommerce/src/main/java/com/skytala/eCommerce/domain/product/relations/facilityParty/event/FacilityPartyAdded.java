package com.skytala.eCommerce.domain.product.relations.facilityParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityParty.model.FacilityParty;
public class FacilityPartyAdded implements Event{

	private FacilityParty addedFacilityParty;
	private boolean success;

	public FacilityPartyAdded(FacilityParty addedFacilityParty, boolean success){
		this.addedFacilityParty = addedFacilityParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityParty getAddedFacilityParty() {
		return addedFacilityParty;
	}

}