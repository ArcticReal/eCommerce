package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;
public class WorkEffortContentFound implements Event{

	private List<WorkEffortContent> workEffortContents;

	public WorkEffortContentFound(List<WorkEffortContent> workEffortContents) {
		this.workEffortContents = workEffortContents;
	}

	public List<WorkEffortContent> getWorkEffortContents()	{
		return workEffortContents;
	}

}
