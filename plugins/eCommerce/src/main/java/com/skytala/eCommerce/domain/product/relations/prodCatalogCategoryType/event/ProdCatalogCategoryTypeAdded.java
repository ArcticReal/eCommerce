package com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.model.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeAdded implements Event{

	private ProdCatalogCategoryType addedProdCatalogCategoryType;
	private boolean success;

	public ProdCatalogCategoryTypeAdded(ProdCatalogCategoryType addedProdCatalogCategoryType, boolean success){
		this.addedProdCatalogCategoryType = addedProdCatalogCategoryType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdCatalogCategoryType getAddedProdCatalogCategoryType() {
		return addedProdCatalogCategoryType;
	}

}
