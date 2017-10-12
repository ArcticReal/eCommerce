package com.skytala.eCommerce.domain.accounting.relations.rateType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;
public class RateTypeFound implements Event{

	private List<RateType> rateTypes;

	public RateTypeFound(List<RateType> rateTypes) {
		this.rateTypes = rateTypes;
	}

	public List<RateType> getRateTypes()	{
		return rateTypes;
	}

}
