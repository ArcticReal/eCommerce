package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
public class ProductStoreGroupRollupFound implements Event{

	private List<ProductStoreGroupRollup> productStoreGroupRollups;

	public ProductStoreGroupRollupFound(List<ProductStoreGroupRollup> productStoreGroupRollups) {
		this.productStoreGroupRollups = productStoreGroupRollups;
	}

	public List<ProductStoreGroupRollup> getProductStoreGroupRollups()	{
		return productStoreGroupRollups;
	}

}
