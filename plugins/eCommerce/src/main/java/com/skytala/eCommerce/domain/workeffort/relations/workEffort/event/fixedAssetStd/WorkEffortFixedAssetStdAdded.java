package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
public class WorkEffortFixedAssetStdAdded implements Event{

	private WorkEffortFixedAssetStd addedWorkEffortFixedAssetStd;
	private boolean success;

	public WorkEffortFixedAssetStdAdded(WorkEffortFixedAssetStd addedWorkEffortFixedAssetStd, boolean success){
		this.addedWorkEffortFixedAssetStd = addedWorkEffortFixedAssetStd;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortFixedAssetStd getAddedWorkEffortFixedAssetStd() {
		return addedWorkEffortFixedAssetStd;
	}

}
