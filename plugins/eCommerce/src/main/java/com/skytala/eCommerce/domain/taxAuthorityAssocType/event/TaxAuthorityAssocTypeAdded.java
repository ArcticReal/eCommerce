package com.skytala.eCommerce.domain.taxAuthorityAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.taxAuthorityAssocType.model.TaxAuthorityAssocType;
public class TaxAuthorityAssocTypeAdded implements Event{

	private TaxAuthorityAssocType addedTaxAuthorityAssocType;
	private boolean success;

	public TaxAuthorityAssocTypeAdded(TaxAuthorityAssocType addedTaxAuthorityAssocType, boolean success){
		this.addedTaxAuthorityAssocType = addedTaxAuthorityAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityAssocType getAddedTaxAuthorityAssocType() {
		return addedTaxAuthorityAssocType;
	}

}
