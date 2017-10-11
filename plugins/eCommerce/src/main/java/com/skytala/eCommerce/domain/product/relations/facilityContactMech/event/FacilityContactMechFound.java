package com.skytala.eCommerce.domain.product.relations.facilityContactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;
public class FacilityContactMechFound implements Event{

	private List<FacilityContactMech> facilityContactMechs;

	public FacilityContactMechFound(List<FacilityContactMech> facilityContactMechs) {
		this.facilityContactMechs = facilityContactMechs;
	}

	public List<FacilityContactMech> getFacilityContactMechs()	{
		return facilityContactMechs;
	}

}
