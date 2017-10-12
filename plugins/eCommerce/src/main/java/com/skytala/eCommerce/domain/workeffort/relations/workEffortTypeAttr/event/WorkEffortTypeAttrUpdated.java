package com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.model.WorkEffortTypeAttr;
public class WorkEffortTypeAttrUpdated implements Event{

	private boolean success;

	public WorkEffortTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
