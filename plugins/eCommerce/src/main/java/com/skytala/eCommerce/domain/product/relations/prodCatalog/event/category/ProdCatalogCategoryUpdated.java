package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;
public class ProdCatalogCategoryUpdated implements Event{

	private boolean success;

	public ProdCatalogCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
