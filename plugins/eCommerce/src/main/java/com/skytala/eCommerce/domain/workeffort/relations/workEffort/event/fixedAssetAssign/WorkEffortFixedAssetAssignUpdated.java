package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetAssign.WorkEffortFixedAssetAssign;
public class WorkEffortFixedAssetAssignUpdated implements Event{

	private boolean success;

	public WorkEffortFixedAssetAssignUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
