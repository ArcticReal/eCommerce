package com.skytala.eCommerce.domain.product.relations.costComponent.event.calc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;
public class CostComponentCalcAdded implements Event{

	private CostComponentCalc addedCostComponentCalc;
	private boolean success;

	public CostComponentCalcAdded(CostComponentCalc addedCostComponentCalc, boolean success){
		this.addedCostComponentCalc = addedCostComponentCalc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CostComponentCalc getAddedCostComponentCalc() {
		return addedCostComponentCalc;
	}

}
