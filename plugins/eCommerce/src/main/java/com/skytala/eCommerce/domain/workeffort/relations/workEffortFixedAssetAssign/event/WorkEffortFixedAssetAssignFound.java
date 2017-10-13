package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;
public class WorkEffortFixedAssetAssignFound implements Event{

	private List<WorkEffortFixedAssetAssign> workEffortFixedAssetAssigns;

	public WorkEffortFixedAssetAssignFound(List<WorkEffortFixedAssetAssign> workEffortFixedAssetAssigns) {
		this.workEffortFixedAssetAssigns = workEffortFixedAssetAssigns;
	}

	public List<WorkEffortFixedAssetAssign> getWorkEffortFixedAssetAssigns()	{
		return workEffortFixedAssetAssigns;
	}

}