package com.skytala.eCommerce.domain.accounting.relations.partyFixedAssetAssignment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyFixedAssetAssignment.model.PartyFixedAssetAssignment;
public class PartyFixedAssetAssignmentUpdated implements Event{

	private boolean success;

	public PartyFixedAssetAssignmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
