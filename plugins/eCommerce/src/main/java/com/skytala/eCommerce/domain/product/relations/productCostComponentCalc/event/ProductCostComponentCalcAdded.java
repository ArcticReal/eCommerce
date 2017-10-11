package com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCostComponentCalc.model.ProductCostComponentCalc;
public class ProductCostComponentCalcAdded implements Event{

	private ProductCostComponentCalc addedProductCostComponentCalc;
	private boolean success;

	public ProductCostComponentCalcAdded(ProductCostComponentCalc addedProductCostComponentCalc, boolean success){
		this.addedProductCostComponentCalc = addedProductCostComponentCalc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCostComponentCalc getAddedProductCostComponentCalc() {
		return addedProductCostComponentCalc;
	}

}
