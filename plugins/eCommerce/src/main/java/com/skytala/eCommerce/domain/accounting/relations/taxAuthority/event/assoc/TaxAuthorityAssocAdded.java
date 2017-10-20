package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.assoc.TaxAuthorityAssoc;
public class TaxAuthorityAssocAdded implements Event{

	private TaxAuthorityAssoc addedTaxAuthorityAssoc;
	private boolean success;

	public TaxAuthorityAssocAdded(TaxAuthorityAssoc addedTaxAuthorityAssoc, boolean success){
		this.addedTaxAuthorityAssoc = addedTaxAuthorityAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityAssoc getAddedTaxAuthorityAssoc() {
		return addedTaxAuthorityAssoc;
	}

}
