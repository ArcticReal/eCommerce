package com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.model.OldPartyTaxInfo;
public class OldPartyTaxInfoDeleted implements Event{

	private boolean success;

	public OldPartyTaxInfoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
