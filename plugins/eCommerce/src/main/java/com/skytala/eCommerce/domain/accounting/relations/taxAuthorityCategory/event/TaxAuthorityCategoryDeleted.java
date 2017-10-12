package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityCategory.model.TaxAuthorityCategory;
public class TaxAuthorityCategoryDeleted implements Event{

	private boolean success;

	public TaxAuthorityCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
