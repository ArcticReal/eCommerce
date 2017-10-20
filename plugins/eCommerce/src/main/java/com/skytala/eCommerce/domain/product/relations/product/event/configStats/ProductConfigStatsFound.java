package com.skytala.eCommerce.domain.product.relations.product.event.configStats;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;
public class ProductConfigStatsFound implements Event{

	private List<ProductConfigStats> productConfigStatss;

	public ProductConfigStatsFound(List<ProductConfigStats> productConfigStatss) {
		this.productConfigStatss = productConfigStatss;
	}

	public List<ProductConfigStats> getProductConfigStatss()	{
		return productConfigStatss;
	}

}
