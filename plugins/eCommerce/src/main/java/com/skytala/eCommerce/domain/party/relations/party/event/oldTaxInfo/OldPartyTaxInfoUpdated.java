package com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;
public class OldPartyTaxInfoUpdated implements Event{

	private boolean success;

	public OldPartyTaxInfoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
