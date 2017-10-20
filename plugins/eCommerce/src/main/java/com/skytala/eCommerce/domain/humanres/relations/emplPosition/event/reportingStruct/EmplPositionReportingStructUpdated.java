package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;
public class EmplPositionReportingStructUpdated implements Event{

	private boolean success;

	public EmplPositionReportingStructUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
