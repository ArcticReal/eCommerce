package com.skytala.eCommerce.domain.humanres.relations.partyResume.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
public class PartyResumeFound implements Event{

	private List<PartyResume> partyResumes;

	public PartyResumeFound(List<PartyResume> partyResumes) {
		this.partyResumes = partyResumes;
	}

	public List<PartyResume> getPartyResumes()	{
		return partyResumes;
	}

}
