package com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.model.ProductAverageCostType;
public class ProductAverageCostTypeFound implements Event{

	private List<ProductAverageCostType> productAverageCostTypes;

	public ProductAverageCostTypeFound(List<ProductAverageCostType> productAverageCostTypes) {
		this.productAverageCostTypes = productAverageCostTypes;
	}

	public List<ProductAverageCostType> getProductAverageCostTypes()	{
		return productAverageCostTypes;
	}

}
