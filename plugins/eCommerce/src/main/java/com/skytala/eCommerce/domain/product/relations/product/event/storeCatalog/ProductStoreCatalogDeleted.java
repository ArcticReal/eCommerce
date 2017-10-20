package com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
public class ProductStoreCatalogDeleted implements Event{

	private boolean success;

	public ProductStoreCatalogDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
