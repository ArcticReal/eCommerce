package com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.model.ProductStoreCatalog;
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
