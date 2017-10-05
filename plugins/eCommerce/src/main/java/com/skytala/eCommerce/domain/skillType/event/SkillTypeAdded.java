package com.skytala.eCommerce.domain.skillType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.skillType.model.SkillType;
public class SkillTypeAdded implements Event{

	private SkillType addedSkillType;
	private boolean success;

	public SkillTypeAdded(SkillType addedSkillType, boolean success){
		this.addedSkillType = addedSkillType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SkillType getAddedSkillType() {
		return addedSkillType;
	}

}
