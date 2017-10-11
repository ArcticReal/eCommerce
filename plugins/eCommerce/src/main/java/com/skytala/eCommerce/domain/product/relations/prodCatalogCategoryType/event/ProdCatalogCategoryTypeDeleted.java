package com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.model.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeDeleted implements Event{

	private boolean success;

	public ProdCatalogCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
