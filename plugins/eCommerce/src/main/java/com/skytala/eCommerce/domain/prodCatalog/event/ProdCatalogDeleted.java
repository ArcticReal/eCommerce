package com.skytala.eCommerce.domain.prodCatalog.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;
public class ProdCatalogDeleted implements Event{

	private boolean success;

	public ProdCatalogDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
