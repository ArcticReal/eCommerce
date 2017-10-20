package com.skytala.eCommerce.domain.product.relations.product.event.storeCatalog;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;
public class ProductStoreCatalogFound implements Event{

	private List<ProductStoreCatalog> productStoreCatalogs;

	public ProductStoreCatalogFound(List<ProductStoreCatalog> productStoreCatalogs) {
		this.productStoreCatalogs = productStoreCatalogs;
	}

	public List<ProductStoreCatalog> getProductStoreCatalogs()	{
		return productStoreCatalogs;
	}

}
