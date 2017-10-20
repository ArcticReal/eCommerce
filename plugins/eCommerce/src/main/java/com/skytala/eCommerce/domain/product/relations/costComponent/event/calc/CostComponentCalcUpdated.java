package com.skytala.eCommerce.domain.product.relations.costComponent.event.calc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;
public class CostComponentCalcUpdated implements Event{

	private boolean success;

	public CostComponentCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
