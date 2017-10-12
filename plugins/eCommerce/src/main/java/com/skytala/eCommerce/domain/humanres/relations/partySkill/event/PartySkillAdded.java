package com.skytala.eCommerce.domain.humanres.relations.partySkill.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
public class PartySkillAdded implements Event{

	private PartySkill addedPartySkill;
	private boolean success;

	public PartySkillAdded(PartySkill addedPartySkill, boolean success){
		this.addedPartySkill = addedPartySkill;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartySkill getAddedPartySkill() {
		return addedPartySkill;
	}

}
