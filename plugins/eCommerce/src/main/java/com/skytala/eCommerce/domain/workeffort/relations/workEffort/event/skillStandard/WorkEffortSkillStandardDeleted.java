package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;
public class WorkEffortSkillStandardDeleted implements Event{

	private boolean success;

	public WorkEffortSkillStandardDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
