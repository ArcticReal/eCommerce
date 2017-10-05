package com.skytala.eCommerce.domain.workReqFulfType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workReqFulfType.model.WorkReqFulfType;
public class WorkReqFulfTypeAdded implements Event{

	private WorkReqFulfType addedWorkReqFulfType;
	private boolean success;

	public WorkReqFulfTypeAdded(WorkReqFulfType addedWorkReqFulfType, boolean success){
		this.addedWorkReqFulfType = addedWorkReqFulfType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkReqFulfType getAddedWorkReqFulfType() {
		return addedWorkReqFulfType;
	}

}
