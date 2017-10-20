package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetAssign.WorkEffortFixedAssetAssign;
public class WorkEffortFixedAssetAssignDeleted implements Event{

	private boolean success;

	public WorkEffortFixedAssetAssignDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
