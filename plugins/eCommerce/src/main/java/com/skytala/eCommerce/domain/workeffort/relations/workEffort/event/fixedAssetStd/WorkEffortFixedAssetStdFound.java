package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
public class WorkEffortFixedAssetStdFound implements Event{

	private List<WorkEffortFixedAssetStd> workEffortFixedAssetStds;

	public WorkEffortFixedAssetStdFound(List<WorkEffortFixedAssetStd> workEffortFixedAssetStds) {
		this.workEffortFixedAssetStds = workEffortFixedAssetStds;
	}

	public List<WorkEffortFixedAssetStd> getWorkEffortFixedAssetStds()	{
		return workEffortFixedAssetStds;
	}

}
