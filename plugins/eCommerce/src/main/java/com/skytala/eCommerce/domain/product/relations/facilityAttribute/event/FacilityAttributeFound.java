package com.skytala.eCommerce.domain.product.relations.facilityAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;
public class FacilityAttributeFound implements Event{

	private List<FacilityAttribute> facilityAttributes;

	public FacilityAttributeFound(List<FacilityAttribute> facilityAttributes) {
		this.facilityAttributes = facilityAttributes;
	}

	public List<FacilityAttribute> getFacilityAttributes()	{
		return facilityAttributes;
	}

}
