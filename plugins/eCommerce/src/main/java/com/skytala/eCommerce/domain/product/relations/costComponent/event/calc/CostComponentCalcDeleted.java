package com.skytala.eCommerce.domain.product.relations.costComponent.event.calc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;
public class CostComponentCalcDeleted implements Event{

	private boolean success;

	public CostComponentCalcDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
