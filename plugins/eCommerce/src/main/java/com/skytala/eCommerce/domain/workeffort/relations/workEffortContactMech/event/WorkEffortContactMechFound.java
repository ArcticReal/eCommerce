package com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.model.WorkEffortContactMech;
public class WorkEffortContactMechFound implements Event{

	private List<WorkEffortContactMech> workEffortContactMechs;

	public WorkEffortContactMechFound(List<WorkEffortContactMech> workEffortContactMechs) {
		this.workEffortContactMechs = workEffortContactMechs;
	}

	public List<WorkEffortContactMech> getWorkEffortContactMechs()	{
		return workEffortContactMechs;
	}

}
