package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;
public class WorkEffortFixedAssetAssignDeleted implements Event{

	private boolean success;

	public WorkEffortFixedAssetAssignDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
