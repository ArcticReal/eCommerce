package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr.WorkEffortAssocTypeAttr;
public class WorkEffortAssocTypeAttrDeleted implements Event{

	private boolean success;

	public WorkEffortAssocTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
