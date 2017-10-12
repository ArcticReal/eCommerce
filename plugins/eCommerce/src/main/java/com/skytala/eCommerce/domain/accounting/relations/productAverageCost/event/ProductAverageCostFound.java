package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
public class ProductAverageCostFound implements Event{

	private List<ProductAverageCost> productAverageCosts;

	public ProductAverageCostFound(List<ProductAverageCost> productAverageCosts) {
		this.productAverageCosts = productAverageCosts;
	}

	public List<ProductAverageCost> getProductAverageCosts()	{
		return productAverageCosts;
	}

}
