package com.skytala.eCommerce.domain.accounting.relations.rateAmount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
public class RateAmountFound implements Event{

	private List<RateAmount> rateAmounts;

	public RateAmountFound(List<RateAmount> rateAmounts) {
		this.rateAmounts = rateAmounts;
	}

	public List<RateAmount> getRateAmounts()	{
		return rateAmounts;
	}

}
