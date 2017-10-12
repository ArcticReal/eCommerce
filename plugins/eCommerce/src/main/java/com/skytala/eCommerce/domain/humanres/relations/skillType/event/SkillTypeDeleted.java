package com.skytala.eCommerce.domain.humanres.relations.skillType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.skillType.model.SkillType;
public class SkillTypeDeleted implements Event{

	private boolean success;

	public SkillTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
