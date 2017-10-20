package com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;
public class ProductCostComponentCalcUpdated implements Event{

	private boolean success;

	public ProductCostComponentCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
