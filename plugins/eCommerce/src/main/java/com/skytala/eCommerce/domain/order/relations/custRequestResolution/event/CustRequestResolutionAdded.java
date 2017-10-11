package com.skytala.eCommerce.domain.order.relations.custRequestResolution.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestResolution.model.CustRequestResolution;
public class CustRequestResolutionAdded implements Event{

	private CustRequestResolution addedCustRequestResolution;
	private boolean success;

	public CustRequestResolutionAdded(CustRequestResolution addedCustRequestResolution, boolean success){
		this.addedCustRequestResolution = addedCustRequestResolution;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestResolution getAddedCustRequestResolution() {
		return addedCustRequestResolution;
	}

}
