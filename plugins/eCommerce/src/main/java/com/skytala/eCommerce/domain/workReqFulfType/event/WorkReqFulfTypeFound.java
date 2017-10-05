package com.skytala.eCommerce.domain.workReqFulfType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workReqFulfType.model.WorkReqFulfType;
public class WorkReqFulfTypeFound implements Event{

	private List<WorkReqFulfType> workReqFulfTypes;

	public WorkReqFulfTypeFound(List<WorkReqFulfType> workReqFulfTypes) {
		this.workReqFulfTypes = workReqFulfTypes;
	}

	public List<WorkReqFulfType> getWorkReqFulfTypes()	{
		return workReqFulfTypes;
	}

}
