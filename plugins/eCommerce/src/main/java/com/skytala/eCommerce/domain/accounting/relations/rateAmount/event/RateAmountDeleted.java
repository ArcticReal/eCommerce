package com.skytala.eCommerce.domain.accounting.relations.rateAmount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
public class RateAmountDeleted implements Event{

	private boolean success;

	public RateAmountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
