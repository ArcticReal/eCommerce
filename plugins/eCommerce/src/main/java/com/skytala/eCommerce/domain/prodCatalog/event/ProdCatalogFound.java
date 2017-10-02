package com.skytala.eCommerce.domain.prodCatalog.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;
public class ProdCatalogFound implements Event{

	private List<ProdCatalog> prodCatalogs;

	public ProdCatalogFound(List<ProdCatalog> prodCatalogs) {
		this.prodCatalogs = prodCatalogs;
	}

	public List<ProdCatalog> getProdCatalogs()	{
		return prodCatalogs;
	}

}
