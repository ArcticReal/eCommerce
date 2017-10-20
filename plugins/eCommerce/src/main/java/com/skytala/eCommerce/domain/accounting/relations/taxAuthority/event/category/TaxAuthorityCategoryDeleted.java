package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.category.TaxAuthorityCategory;
public class TaxAuthorityCategoryDeleted implements Event{

	private boolean success;

	public TaxAuthorityCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
