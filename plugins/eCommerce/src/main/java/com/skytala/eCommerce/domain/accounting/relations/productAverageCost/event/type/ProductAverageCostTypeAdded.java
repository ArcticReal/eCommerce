package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.type.ProductAverageCostType;
public class ProductAverageCostTypeAdded implements Event{

	private ProductAverageCostType addedProductAverageCostType;
	private boolean success;

	public ProductAverageCostTypeAdded(ProductAverageCostType addedProductAverageCostType, boolean success){
		this.addedProductAverageCostType = addedProductAverageCostType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductAverageCostType getAddedProductAverageCostType() {
		return addedProductAverageCostType;
	}

}
