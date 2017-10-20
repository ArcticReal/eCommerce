package com.skytala.eCommerce.domain.product.relations.costComponent.event.calc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.calc.CostComponentCalc;
public class CostComponentCalcFound implements Event{

	private List<CostComponentCalc> costComponentCalcs;

	public CostComponentCalcFound(List<CostComponentCalc> costComponentCalcs) {
		this.costComponentCalcs = costComponentCalcs;
	}

	public List<CostComponentCalc> getCostComponentCalcs()	{
		return costComponentCalcs;
	}

}
