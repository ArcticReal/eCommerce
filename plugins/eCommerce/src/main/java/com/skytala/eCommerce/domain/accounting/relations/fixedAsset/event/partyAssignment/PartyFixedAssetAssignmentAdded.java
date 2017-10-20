package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
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
