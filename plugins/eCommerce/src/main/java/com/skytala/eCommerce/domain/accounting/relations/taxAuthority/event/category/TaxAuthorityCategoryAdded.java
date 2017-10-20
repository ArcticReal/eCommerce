package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;
public class TaxAuthorityCategoryAdded implements Event{

	private TaxAuthorityCategory addedTaxAuthorityCategory;
	private boolean success;

	public TaxAuthorityCategoryAdded(TaxAuthorityCategory addedTaxAuthorityCategory, boolean success){
		this.addedTaxAuthorityCategory = addedTaxAuthorityCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityCategory getAddedTaxAuthorityCategory() {
		return addedTaxAuthorityCategory;
	}

}
