package com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;
public class OldPartyTaxInfoAdded implements Event{

	private OldPartyTaxInfo addedOldPartyTaxInfo;
	private boolean success;

	public OldPartyTaxInfoAdded(OldPartyTaxInfo addedOldPartyTaxInfo, boolean success){
		this.addedOldPartyTaxInfo = addedOldPartyTaxInfo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OldPartyTaxInfo getAddedOldPartyTaxInfo() {
		return addedOldPartyTaxInfo;
	}

}
