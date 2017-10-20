package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;
public class WorkEffortAttributeFound implements Event{

	private List<WorkEffortAttribute> workEffortAttributes;

	public WorkEffortAttributeFound(List<WorkEffortAttribute> workEffortAttributes) {
		this.workEffortAttributes = workEffortAttributes;
	}

	public List<WorkEffortAttribute> getWorkEffortAttributes()	{
		return workEffortAttributes;
	}

}
