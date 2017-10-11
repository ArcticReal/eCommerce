package com.skytala.eCommerce.domain.product.relations.facility.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;
public class FacilityFound implements Event{

	private List<Facility> facilitys;

	public FacilityFound(List<Facility> facilitys) {
		this.facilitys = facilitys;
	}

	public List<Facility> getFacilitys()	{
		return facilitys;
	}

}
