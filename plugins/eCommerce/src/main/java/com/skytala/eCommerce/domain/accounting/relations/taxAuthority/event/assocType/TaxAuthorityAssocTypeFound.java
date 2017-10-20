package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assocType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assocType.TaxAuthorityAssocType;
public class TaxAuthorityAssocTypeFound implements Event{

	private List<TaxAuthorityAssocType> taxAuthorityAssocTypes;

	public TaxAuthorityAssocTypeFound(List<TaxAuthorityAssocType> taxAuthorityAssocTypes) {
		this.taxAuthorityAssocTypes = taxAuthorityAssocTypes;
	}

	public List<TaxAuthorityAssocType> getTaxAuthorityAssocTypes()	{
		return taxAuthorityAssocTypes;
	}

}
