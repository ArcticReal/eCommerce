package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.model.WorkEffortAssocAttribute;
public class WorkEffortAssocAttributeFound implements Event{

	private List<WorkEffortAssocAttribute> workEffortAssocAttributes;

	public WorkEffortAssocAttributeFound(List<WorkEffortAssocAttribute> workEffortAssocAttributes) {
		this.workEffortAssocAttributes = workEffortAssocAttributes;
	}

	public List<WorkEffortAssocAttribute> getWorkEffortAssocAttributes()	{
		return workEffortAssocAttributes;
	}

}
