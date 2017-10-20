package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.typeAttr.WorkEffortTypeAttr;
public class WorkEffortTypeAttrDeleted implements Event{

	private boolean success;

	public WorkEffortTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
