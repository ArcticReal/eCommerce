package com.skytala.eCommerce.domain.workEffortPurposeType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workEffortPurposeType.model.WorkEffortPurposeType;
public class WorkEffortPurposeTypeFound implements Event{

	private List<WorkEffortPurposeType> workEffortPurposeTypes;

	public WorkEffortPurposeTypeFound(List<WorkEffortPurposeType> workEffortPurposeTypes) {
		this.workEffortPurposeTypes = workEffortPurposeTypes;
	}

	public List<WorkEffortPurposeType> getWorkEffortPurposeTypes()	{
		return workEffortPurposeTypes;
	}

}
