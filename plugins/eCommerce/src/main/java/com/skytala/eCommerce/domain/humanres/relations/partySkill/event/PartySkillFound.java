package com.skytala.eCommerce.domain.humanres.relations.partySkill.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
public class PartySkillFound implements Event{

	private List<PartySkill> partySkills;

	public PartySkillFound(List<PartySkill> partySkills) {
		this.partySkills = partySkills;
	}

	public List<PartySkill> getPartySkills()	{
		return partySkills;
	}

}
