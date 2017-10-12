package com.skytala.eCommerce.domain.accounting.relations.rateAmount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
public class RateAmountAdded implements Event{

	private RateAmount addedRateAmount;
	private boolean success;

	public RateAmountAdded(RateAmount addedRateAmount, boolean success){
		this.addedRateAmount = addedRateAmount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RateAmount getAddedRateAmount() {
		return addedRateAmount;
	}

}
