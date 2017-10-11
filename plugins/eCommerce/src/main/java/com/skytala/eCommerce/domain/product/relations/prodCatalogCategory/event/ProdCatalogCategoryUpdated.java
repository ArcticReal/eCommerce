package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model.ProdCatalogCategory;
public class ProdCatalogCategoryUpdated implements Event{

	private boolean success;

	public ProdCatalogCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
