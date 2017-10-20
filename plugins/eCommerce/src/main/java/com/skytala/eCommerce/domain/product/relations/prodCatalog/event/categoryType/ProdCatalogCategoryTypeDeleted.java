package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeDeleted implements Event{

	private boolean success;

	public ProdCatalogCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
