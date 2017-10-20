package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;
public class TaxAuthorityCategoryFound implements Event{

	private List<TaxAuthorityCategory> taxAuthorityCategorys;

	public TaxAuthorityCategoryFound(List<TaxAuthorityCategory> taxAuthorityCategorys) {
		this.taxAuthorityCategorys = taxAuthorityCategorys;
	}

	public List<TaxAuthorityCategory> getTaxAuthorityCategorys()	{
		return taxAuthorityCategorys;
	}

}
