package com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;
public class ProductCategoryRollupFound implements Event{

	private List<ProductCategoryRollup> productCategoryRollups;

	public ProductCategoryRollupFound(List<ProductCategoryRollup> productCategoryRollups) {
		this.productCategoryRollups = productCategoryRollups;
	}

	public List<ProductCategoryRollup> getProductCategoryRollups()	{
		return productCategoryRollups;
	}

}
