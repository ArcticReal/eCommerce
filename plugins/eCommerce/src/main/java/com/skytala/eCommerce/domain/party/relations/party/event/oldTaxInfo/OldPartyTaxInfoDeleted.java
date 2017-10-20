package com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;
public class OldPartyTaxInfoDeleted implements Event{

	private boolean success;

	public OldPartyTaxInfoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
