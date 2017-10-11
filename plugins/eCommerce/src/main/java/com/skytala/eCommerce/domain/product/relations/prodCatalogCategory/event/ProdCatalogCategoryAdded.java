package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model.ProdCatalogCategory;
public class ProdCatalogCategoryAdded implements Event{

	private ProdCatalogCategory addedProdCatalogCategory;
	private boolean success;

	public ProdCatalogCategoryAdded(ProdCatalogCategory addedProdCatalogCategory, boolean success){
		this.addedProdCatalogCategory = addedProdCatalogCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdCatalogCategory getAddedProdCatalogCategory() {
		return addedProdCatalogCategory;
	}

}
