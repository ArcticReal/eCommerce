package com.skytala.eCommerce.domain.order.relations.custRequest.event.resolution;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.resolution.CustRequestResolution;
public class CustRequestResolutionFound implements Event{

	private List<CustRequestResolution> custRequestResolutions;

	public CustRequestResolutionFound(List<CustRequestResolution> custRequestResolutions) {
		this.custRequestResolutions = custRequestResolutions;
	}

	public List<CustRequestResolution> getCustRequestResolutions()	{
		return custRequestResolutions;
	}

}
