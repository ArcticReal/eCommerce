package com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.model.ProductStoreCatalog;
public class ProductStoreCatalogFound implements Event{

	private List<ProductStoreCatalog> productStoreCatalogs;

	public ProductStoreCatalogFound(List<ProductStoreCatalog> productStoreCatalogs) {
		this.productStoreCatalogs = productStoreCatalogs;
	}

	public List<ProductStoreCatalog> getProductStoreCatalogs()	{
		return productStoreCatalogs;
	}

}
