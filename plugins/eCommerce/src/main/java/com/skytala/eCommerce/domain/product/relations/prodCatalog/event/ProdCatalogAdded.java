package com.skytala.eCommerce.domain.product.relations.prodCatalog.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.ProdCatalog;
public class ProdCatalogAdded implements Event{

	private ProdCatalog addedProdCatalog;
	private boolean success;

	public ProdCatalogAdded(ProdCatalog addedProdCatalog, boolean success){
		this.addedProdCatalog = addedProdCatalog;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdCatalog getAddedProdCatalog() {
		return addedProdCatalog;
	}

}
