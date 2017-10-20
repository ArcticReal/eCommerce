package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;
public class WorkEffortPurposeTypeFound implements Event{

	private List<WorkEffortPurposeType> workEffortPurposeTypes;

	public WorkEffortPurposeTypeFound(List<WorkEffortPurposeType> workEffortPurposeTypes) {
		this.workEffortPurposeTypes = workEffortPurposeTypes;
	}

	public List<WorkEffortPurposeType> getWorkEffortPurposeTypes()	{
		return workEffortPurposeTypes;
	}

}
