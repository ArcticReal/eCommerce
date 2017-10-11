package com.skytala.eCommerce.domain.product.relations.productStoreGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroup.model.ProductStoreGroup;
public class ProductStoreGroupFound implements Event{

	private List<ProductStoreGroup> productStoreGroups;

	public ProductStoreGroupFound(List<ProductStoreGroup> productStoreGroups) {
		this.productStoreGroups = productStoreGroups;
	}

	public List<ProductStoreGroup> getProductStoreGroups()	{
		return productStoreGroups;
	}

}
