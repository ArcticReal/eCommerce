package com.skytala.eCommerce.domain.product.relations.prodCatalog.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.ProdCatalog;
public class ProdCatalogUpdated implements Event{

	private boolean success;

	public ProdCatalogUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
