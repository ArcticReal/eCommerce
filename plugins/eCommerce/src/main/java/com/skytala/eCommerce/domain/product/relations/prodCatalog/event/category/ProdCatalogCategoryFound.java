package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.category;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.category.ProdCatalogCategory;
public class ProdCatalogCategoryFound implements Event{

	private List<ProdCatalogCategory> prodCatalogCategorys;

	public ProdCatalogCategoryFound(List<ProdCatalogCategory> prodCatalogCategorys) {
		this.prodCatalogCategorys = prodCatalogCategorys;
	}

	public List<ProdCatalogCategory> getProdCatalogCategorys()	{
		return prodCatalogCategorys;
	}

}
