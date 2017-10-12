package com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.model.WorkEffortContactMech;
public class WorkEffortContactMechAdded implements Event{

	private WorkEffortContactMech addedWorkEffortContactMech;
	private boolean success;

	public WorkEffortContactMechAdded(WorkEffortContactMech addedWorkEffortContactMech, boolean success){
		this.addedWorkEffortContactMech = addedWorkEffortContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortContactMech getAddedWorkEffortContactMech() {
		return addedWorkEffortContactMech;
	}

}
