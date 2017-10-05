package com.skytala.eCommerce.domain.prodCatalogCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.prodCatalogCategoryType.model.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeUpdated implements Event{

	private boolean success;

	public ProdCatalogCategoryTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
