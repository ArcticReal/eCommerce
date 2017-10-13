package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.model.WorkEffortAssocTypeAttr;
public class WorkEffortAssocTypeAttrFound implements Event{

	private List<WorkEffortAssocTypeAttr> workEffortAssocTypeAttrs;

	public WorkEffortAssocTypeAttrFound(List<WorkEffortAssocTypeAttr> workEffortAssocTypeAttrs) {
		this.workEffortAssocTypeAttrs = workEffortAssocTypeAttrs;
	}

	public List<WorkEffortAssocTypeAttr> getWorkEffortAssocTypeAttrs()	{
		return workEffortAssocTypeAttrs;
	}

}