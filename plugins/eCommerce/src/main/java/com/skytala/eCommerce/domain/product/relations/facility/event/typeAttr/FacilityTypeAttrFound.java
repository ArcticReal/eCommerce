package com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;
public class FacilityTypeAttrFound implements Event{

	private List<FacilityTypeAttr> facilityTypeAttrs;

	public FacilityTypeAttrFound(List<FacilityTypeAttr> facilityTypeAttrs) {
		this.facilityTypeAttrs = facilityTypeAttrs;
	}

	public List<FacilityTypeAttr> getFacilityTypeAttrs()	{
		return facilityTypeAttrs;
	}

}
