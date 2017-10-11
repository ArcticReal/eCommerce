package com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogCategoryType.model.ProdCatalogCategoryType;
public class ProdCatalogCategoryTypeFound implements Event{

	private List<ProdCatalogCategoryType> prodCatalogCategoryTypes;

	public ProdCatalogCategoryTypeFound(List<ProdCatalogCategoryType> prodCatalogCategoryTypes) {
		this.prodCatalogCategoryTypes = prodCatalogCategoryTypes;
	}

	public List<ProdCatalogCategoryType> getProdCatalogCategoryTypes()	{
		return prodCatalogCategoryTypes;
	}

}
