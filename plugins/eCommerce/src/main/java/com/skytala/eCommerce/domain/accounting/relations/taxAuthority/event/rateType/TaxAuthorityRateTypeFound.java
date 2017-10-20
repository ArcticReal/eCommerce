package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateType.TaxAuthorityRateType;
public class TaxAuthorityRateTypeFound implements Event{

	private List<TaxAuthorityRateType> taxAuthorityRateTypes;

	public TaxAuthorityRateTypeFound(List<TaxAuthorityRateType> taxAuthorityRateTypes) {
		this.taxAuthorityRateTypes = taxAuthorityRateTypes;
	}

	public List<TaxAuthorityRateType> getTaxAuthorityRateTypes()	{
		return taxAuthorityRateTypes;
	}

}
