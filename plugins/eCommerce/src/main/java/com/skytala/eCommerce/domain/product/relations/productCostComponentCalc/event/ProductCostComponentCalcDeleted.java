package com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.model.ProductCostComponentCalc;
public class ProductCostComponentCalcDeleted implements Event{

	private boolean success;

	public ProductCostComponentCalcDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
