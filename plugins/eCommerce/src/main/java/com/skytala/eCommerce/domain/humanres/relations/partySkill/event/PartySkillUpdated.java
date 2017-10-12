package com.skytala.eCommerce.domain.humanres.relations.partySkill.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
public class PartySkillUpdated implements Event{

	private boolean success;

	public PartySkillUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
