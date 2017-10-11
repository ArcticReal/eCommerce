package com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.model.ProductStoreCatalog;
public class ProductStoreCatalogUpdated implements Event{

	private boolean success;

	public ProductStoreCatalogUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
