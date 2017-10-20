package com.skytala.eCommerce.domain.product.relations.costComponent.event.productCalc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.productCalc.ProductCostComponentCalc;
public class ProductCostComponentCalcFound implements Event{

	private List<ProductCostComponentCalc> productCostComponentCalcs;

	public ProductCostComponentCalcFound(List<ProductCostComponentCalc> productCostComponentCalcs) {
		this.productCostComponentCalcs = productCostComponentCalcs;
	}

	public List<ProductCostComponentCalc> getProductCostComponentCalcs()	{
		return productCostComponentCalcs;
	}

}
