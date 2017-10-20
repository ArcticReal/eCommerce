package com.skytala.eCommerce.domain.product.relations.facility.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.content.FacilityContent;
public class FacilityContentFound implements Event{

	private List<FacilityContent> facilityContents;

	public FacilityContentFound(List<FacilityContent> facilityContents) {
		this.facilityContents = facilityContents;
	}

	public List<FacilityContent> getFacilityContents()	{
		return facilityContents;
	}

}
