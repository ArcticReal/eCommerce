package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.model.TaxAuthorityCategory;
public class TaxAuthorityCategoryUpdated implements Event{

	private boolean success;

	public TaxAuthorityCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
