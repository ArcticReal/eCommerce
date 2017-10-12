package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.model.TaxAuthorityCategory;
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
