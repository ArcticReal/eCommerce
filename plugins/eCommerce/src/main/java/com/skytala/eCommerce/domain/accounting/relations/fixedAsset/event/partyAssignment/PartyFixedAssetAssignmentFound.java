package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
public class PartyFixedAssetAssignmentFound implements Event{

	private List<PartyFixedAssetAssignment> partyFixedAssetAssignments;

	public PartyFixedAssetAssignmentFound(List<PartyFixedAssetAssignment> partyFixedAssetAssignments) {
		this.partyFixedAssetAssignments = partyFixedAssetAssignments;
	}

	public List<PartyFixedAssetAssignment> getPartyFixedAssetAssignments()	{
		return partyFixedAssetAssignments;
	}

}
