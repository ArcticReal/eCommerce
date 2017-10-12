package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.model.WorkEffortFixedAssetStd;
public class WorkEffortFixedAssetStdDeleted implements Event{

	private boolean success;

	public WorkEffortFixedAssetStdDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
