package com.skytala.eCommerce.domain.skillType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.skillType.model.SkillType;
public class SkillTypeUpdated implements Event{

	private boolean success;

	public SkillTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
