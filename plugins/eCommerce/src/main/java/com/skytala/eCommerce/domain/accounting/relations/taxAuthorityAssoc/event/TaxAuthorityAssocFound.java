package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model.TaxAuthorityAssoc;
public class TaxAuthorityAssocFound implements Event{

	private List<TaxAuthorityAssoc> taxAuthorityAssocs;

	public TaxAuthorityAssocFound(List<TaxAuthorityAssoc> taxAuthorityAssocs) {
		this.taxAuthorityAssocs = taxAuthorityAssocs;
	}

	public List<TaxAuthorityAssoc> getTaxAuthorityAssocs()	{
		return taxAuthorityAssocs;
	}

}
