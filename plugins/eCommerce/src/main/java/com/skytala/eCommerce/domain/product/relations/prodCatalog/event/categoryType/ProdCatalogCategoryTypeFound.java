package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.categoryType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.categoryType.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeFound implements Event{

	private List<ProdCatalogCategoryType> prodCatalogCategoryTypes;

	public ProdCatalogCategoryTypeFound(List<ProdCatalogCategoryType> prodCatalogCategoryTypes) {
		this.prodCatalogCategoryTypes = prodCatalogCategoryTypes;
	}

	public List<ProdCatalogCategoryType> getProdCatalogCategoryTypes()	{
		return prodCatalogCategoryTypes;
	}

}
