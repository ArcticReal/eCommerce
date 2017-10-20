package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contentType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contentType.WorkEffortContentType;
public class WorkEffortContentTypeFound implements Event{

	private List<WorkEffortContentType> workEffortContentTypes;

	public WorkEffortContentTypeFound(List<WorkEffortContentType> workEffortContentTypes) {
		this.workEffortContentTypes = workEffortContentTypes;
	}

	public List<WorkEffortContentType> getWorkEffortContentTypes()	{
		return workEffortContentTypes;
	}

}
