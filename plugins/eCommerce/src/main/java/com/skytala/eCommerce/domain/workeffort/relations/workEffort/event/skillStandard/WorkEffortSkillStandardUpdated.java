package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
public class WorkEffortSkillStandardUpdated implements Event{

	private boolean success;

	public WorkEffortSkillStandardUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
