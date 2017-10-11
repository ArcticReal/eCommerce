package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model.ProdCatalogCategory;
public class ProdCatalogCategoryDeleted implements Event{

	private boolean success;

	public ProdCatalogCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
