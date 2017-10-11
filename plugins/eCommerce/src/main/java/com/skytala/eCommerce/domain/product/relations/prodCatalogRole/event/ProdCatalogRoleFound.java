package com.skytala.eCommerce.domain.product.relations.prodCatalogRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.model.ProdCatalogRole;
public class ProdCatalogRoleFound implements Event{

	private List<ProdCatalogRole> prodCatalogRoles;

	public ProdCatalogRoleFound(List<ProdCatalogRole> prodCatalogRoles) {
		this.prodCatalogRoles = prodCatalogRoles;
	}

	public List<ProdCatalogRole> getProdCatalogRoles()	{
		return prodCatalogRoles;
	}

}
