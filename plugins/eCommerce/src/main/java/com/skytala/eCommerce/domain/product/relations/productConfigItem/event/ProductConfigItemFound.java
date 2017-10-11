package com.skytala.eCommerce.domain.product.relations.productConfigItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigItem.model.ProductConfigItem;
public class ProductConfigItemFound implements Event{

	private List<ProductConfigItem> productConfigItems;

	public ProductConfigItemFound(List<ProductConfigItem> productConfigItems) {
		this.productConfigItems = productConfigItems;
	}

	public List<ProductConfigItem> getProductConfigItems()	{
		return productConfigItems;
	}

}
