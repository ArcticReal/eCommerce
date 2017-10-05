package com.skytala.eCommerce.domain.rateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.rateType.model.RateType;
public class RateTypeAdded implements Event{

	private RateType addedRateType;
	private boolean success;

	public RateTypeAdded(RateType addedRateType, boolean success){
		this.addedRateType = addedRateType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RateType getAddedRateType() {
		return addedRateType;
	}

}
