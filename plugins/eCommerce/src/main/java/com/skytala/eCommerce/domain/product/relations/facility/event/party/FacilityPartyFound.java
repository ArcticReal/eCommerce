package com.skytala.eCommerce.domain.product.relations.facility.event.party;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;
public class FacilityPartyFound implements Event{

	private List<FacilityParty> facilityPartys;

	public FacilityPartyFound(List<FacilityParty> facilityPartys) {
		this.facilityPartys = facilityPartys;
	}

	public List<FacilityParty> getFacilityPartys()	{
		return facilityPartys;
	}

}
