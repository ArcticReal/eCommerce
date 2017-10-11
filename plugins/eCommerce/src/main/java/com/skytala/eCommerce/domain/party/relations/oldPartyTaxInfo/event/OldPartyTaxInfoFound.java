package com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.model.OldPartyTaxInfo;
public class OldPartyTaxInfoFound implements Event{

	private List<OldPartyTaxInfo> oldPartyTaxInfos;

	public OldPartyTaxInfoFound(List<OldPartyTaxInfo> oldPartyTaxInfos) {
		this.oldPartyTaxInfos = oldPartyTaxInfos;
	}

	public List<OldPartyTaxInfo> getOldPartyTaxInfos()	{
		return oldPartyTaxInfos;
	}

}
