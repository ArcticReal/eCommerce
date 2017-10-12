package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
public class ProductAverageCostAdded implements Event{

	private ProductAverageCost addedProductAverageCost;
	private boolean success;

	public ProductAverageCostAdded(ProductAverageCost addedProductAverageCost, boolean success){
		this.addedProductAverageCost = addedProductAverageCost;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductAverageCost getAddedProductAverageCost() {
		return addedProductAverageCost;
	}

}
