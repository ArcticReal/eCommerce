package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;
public class WorkEffortContactMechFound implements Event{

	private List<WorkEffortContactMech> workEffortContactMechs;

	public WorkEffortContactMechFound(List<WorkEffortContactMech> workEffortContactMechs) {
		this.workEffortContactMechs = workEffortContactMechs;
	}

	public List<WorkEffortContactMech> getWorkEffortContactMechs()	{
		return workEffortContactMechs;
	}

}
