package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
public class WorkEffortFixedAssetStdDeleted implements Event{

	private boolean success;

	public WorkEffortFixedAssetStdDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
