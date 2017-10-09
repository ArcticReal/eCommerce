package com.skytala.eCommerce.domain.skillType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.skillType.model.SkillType;
public class SkillTypeFound implements Event{

	private List<SkillType> skillTypes;

	public SkillTypeFound(List<SkillType> skillTypes) {
		this.skillTypes = skillTypes;
	}

	public List<SkillType> getSkillTypes()	{
		return skillTypes;
	}

}