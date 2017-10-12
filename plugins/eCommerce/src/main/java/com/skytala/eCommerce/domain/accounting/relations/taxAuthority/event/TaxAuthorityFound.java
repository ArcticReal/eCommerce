package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;
public class TaxAuthorityFound implements Event{

	private List<TaxAuthority> taxAuthoritys;

	public TaxAuthorityFound(List<TaxAuthority> taxAuthoritys) {
		this.taxAuthoritys = taxAuthoritys;
	}

	public List<TaxAuthority> getTaxAuthoritys()	{
		return taxAuthoritys;
	}

}
