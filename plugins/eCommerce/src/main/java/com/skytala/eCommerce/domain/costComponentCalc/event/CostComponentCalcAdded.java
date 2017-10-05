package com.skytala.eCommerce.domain.costComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponentCalc.model.CostComponentCalc;
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
