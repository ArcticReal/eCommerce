package com.skytala.eCommerce.domain.product.relations.product.event.store;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.store.ProductStore;
public class ProductStoreFound implements Event{

	private List<ProductStore> productStores;

	public ProductStoreFound(List<ProductStore> productStores) {
		this.productStores = productStores;
	}

	public List<ProductStore> getProductStores()	{
		return productStores;
	}

}
