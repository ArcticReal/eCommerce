package com.skytala.eCommerce.domain.accounting.relations.partyFixedAssetAssignment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyFixedAssetAssignment.model.PartyFixedAssetAssignment;
public class PartyFixedAssetAssignmentAdded implements Event{

	private PartyFixedAssetAssignment addedPartyFixedAssetAssignment;
	private boolean success;

	public PartyFixedAssetAssignmentAdded(PartyFixedAssetAssignment addedPartyFixedAssetAssignment, boolean success){
		this.addedPartyFixedAssetAssignment = addedPartyFixedAssetAssignment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyFixedAssetAssignment getAddedPartyFixedAssetAssignment() {
		return addedPartyFixedAssetAssignment;
	}

}
