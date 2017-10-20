package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
public class WorkEffortFixedAssetStdUpdated implements Event{

	private boolean success;

	public WorkEffortFixedAssetStdUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
