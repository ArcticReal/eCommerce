package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;
public class WorkEffortFixedAssetAssignAdded implements Event{

	private WorkEffortFixedAssetAssign addedWorkEffortFixedAssetAssign;
	private boolean success;

	public WorkEffortFixedAssetAssignAdded(WorkEffortFixedAssetAssign addedWorkEffortFixedAssetAssign, boolean success){
		this.addedWorkEffortFixedAssetAssign = addedWorkEffortFixedAssetAssign;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortFixedAssetAssign getAddedWorkEffortFixedAssetAssign() {
		return addedWorkEffortFixedAssetAssign;
	}

}
