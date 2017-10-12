package com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortSkillStandard.model.WorkEffortSkillStandard;
public class WorkEffortSkillStandardDeleted implements Event{

	private boolean success;

	public WorkEffortSkillStandardDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
