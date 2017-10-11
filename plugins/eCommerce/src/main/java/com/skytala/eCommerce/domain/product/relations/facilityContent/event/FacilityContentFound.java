package com.skytala.eCommerce.domain.product.relations.facilityContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;
public class FacilityContentFound implements Event{

	private List<FacilityContent> facilityContents;

	public FacilityContentFound(List<FacilityContent> facilityContents) {
		this.facilityContents = facilityContents;
	}

	public List<FacilityContent> getFacilityContents()	{
		return facilityContents;
	}

}
