package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateType.model.TaxAuthorityRateType;
public class TaxAuthorityRateTypeAdded implements Event{

	private TaxAuthorityRateType addedTaxAuthorityRateType;
	private boolean success;

	public TaxAuthorityRateTypeAdded(TaxAuthorityRateType addedTaxAuthorityRateType, boolean success){
		this.addedTaxAuthorityRateType = addedTaxAuthorityRateType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityRateType getAddedTaxAuthorityRateType() {
		return addedTaxAuthorityRateType;
	}

}
