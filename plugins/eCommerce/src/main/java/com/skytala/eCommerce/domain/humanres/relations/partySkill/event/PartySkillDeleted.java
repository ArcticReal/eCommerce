package com.skytala.eCommerce.domain.humanres.relations.partySkill.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
public class PartySkillDeleted implements Event{

	private boolean success;

	public PartySkillDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
