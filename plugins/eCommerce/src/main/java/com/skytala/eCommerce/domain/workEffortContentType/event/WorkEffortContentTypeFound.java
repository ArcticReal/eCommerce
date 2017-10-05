package com.skytala.eCommerce.domain.workEffortContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortContentType.model.WorkEffortContentType;
public class WorkEffortContentTypeFound implements Event{

	private List<WorkEffortContentType> workEffortContentTypes;

	public WorkEffortContentTypeFound(List<WorkEffortContentType> workEffortContentTypes) {
		this.workEffortContentTypes = workEffortContentTypes;
	}

	public List<WorkEffortContentType> getWorkEffortContentTypes()	{
		return workEffortContentTypes;
	}

}
