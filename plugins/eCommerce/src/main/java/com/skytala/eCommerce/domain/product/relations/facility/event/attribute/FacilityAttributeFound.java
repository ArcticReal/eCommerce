package com.skytala.eCommerce.domain.product.relations.facility.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.attribute.FacilityAttribute;
public class FacilityAttributeFound implements Event{

	private List<FacilityAttribute> facilityAttributes;

	public FacilityAttributeFound(List<FacilityAttribute> facilityAttributes) {
		this.facilityAttributes = facilityAttributes;
	}

	public List<FacilityAttribute> getFacilityAttributes()	{
		return facilityAttributes;
	}

}
