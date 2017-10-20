package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
public class ProdCatalogInvFacilityFound implements Event{

	private List<ProdCatalogInvFacility> prodCatalogInvFacilitys;

	public ProdCatalogInvFacilityFound(List<ProdCatalogInvFacility> prodCatalogInvFacilitys) {
		this.prodCatalogInvFacilitys = prodCatalogInvFacilitys;
	}

	public List<ProdCatalogInvFacility> getProdCatalogInvFacilitys()	{
		return prodCatalogInvFacilitys;
	}

}
