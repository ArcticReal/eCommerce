package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.typeAttr.WorkEffortTypeAttr;
public class WorkEffortTypeAttrFound implements Event{

	private List<WorkEffortTypeAttr> workEffortTypeAttrs;

	public WorkEffortTypeAttrFound(List<WorkEffortTypeAttr> workEffortTypeAttrs) {
		this.workEffortTypeAttrs = workEffortTypeAttrs;
	}

	public List<WorkEffortTypeAttr> getWorkEffortTypeAttrs()	{
		return workEffortTypeAttrs;
	}

}
