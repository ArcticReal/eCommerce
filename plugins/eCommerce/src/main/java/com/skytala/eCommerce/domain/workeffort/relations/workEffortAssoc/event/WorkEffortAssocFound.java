package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;
public class WorkEffortAssocFound implements Event{

	private List<WorkEffortAssoc> workEffortAssocs;

	public WorkEffortAssocFound(List<WorkEffortAssoc> workEffortAssocs) {
		this.workEffortAssocs = workEffortAssocs;
	}

	public List<WorkEffortAssoc> getWorkEffortAssocs()	{
		return workEffortAssocs;
	}

}
