package com.skytala.eCommerce.domain.product.relations.costComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentCalc.model.CostComponentCalc;
public class CostComponentCalcUpdated implements Event{

	private boolean success;

	public CostComponentCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
