package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
public class PartyFixedAssetAssignmentUpdated implements Event{

	private boolean success;

	public PartyFixedAssetAssignmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
