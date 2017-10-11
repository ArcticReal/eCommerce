package com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.model.ProductCostComponentCalc;
public class ProductCostComponentCalcUpdated implements Event{

	private boolean success;

	public ProductCostComponentCalcUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
