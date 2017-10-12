package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
public class TaxAuthorityAdded implements Event{

	private TaxAuthority addedTaxAuthority;
	private boolean success;

	public TaxAuthorityAdded(TaxAuthority addedTaxAuthority, boolean success){
		this.addedTaxAuthority = addedTaxAuthority;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthority getAddedTaxAuthority() {
		return addedTaxAuthority;
	}

}
