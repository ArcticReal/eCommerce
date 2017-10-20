package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData.WorkEffortIcalData;
public class WorkEffortIcalDataUpdated implements Event{

	private boolean success;

	public WorkEffortIcalDataUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
