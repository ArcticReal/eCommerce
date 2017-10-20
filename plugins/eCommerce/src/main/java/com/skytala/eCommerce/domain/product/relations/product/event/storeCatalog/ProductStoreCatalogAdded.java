package com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
public class ProductStoreCatalogAdded implements Event{

	private ProductStoreCatalog addedProductStoreCatalog;
	private boolean success;

	public ProductStoreCatalogAdded(ProductStoreCatalog addedProductStoreCatalog, boolean success){
		this.addedProductStoreCatalog = addedProductStoreCatalog;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreCatalog getAddedProductStoreCatalog() {
		return addedProductStoreCatalog;
	}

}
