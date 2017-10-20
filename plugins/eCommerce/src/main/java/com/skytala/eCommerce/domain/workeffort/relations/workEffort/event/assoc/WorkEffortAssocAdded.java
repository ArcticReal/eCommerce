package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assoc.WorkEffortAssoc;
public class WorkEffortAssocAdded implements Event{

	private WorkEffortAssoc addedWorkEffortAssoc;
	private boolean success;

	public WorkEffortAssocAdded(WorkEffortAssoc addedWorkEffortAssoc, boolean success){
		this.addedWorkEffortAssoc = addedWorkEffortAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortAssoc getAddedWorkEffortAssoc() {
		return addedWorkEffortAssoc;
	}

}
