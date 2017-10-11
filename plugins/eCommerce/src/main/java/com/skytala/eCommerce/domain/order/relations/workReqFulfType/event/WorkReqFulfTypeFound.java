package com.skytala.eCommerce.domain.order.relations.workReqFulfType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;
public class WorkReqFulfTypeFound implements Event{

	private List<WorkReqFulfType> workReqFulfTypes;

	public WorkReqFulfTypeFound(List<WorkReqFulfType> workReqFulfTypes) {
		this.workReqFulfTypes = workReqFulfTypes;
	}

	public List<WorkReqFulfType> getWorkReqFulfTypes()	{
		return workReqFulfTypes;
	}

}
