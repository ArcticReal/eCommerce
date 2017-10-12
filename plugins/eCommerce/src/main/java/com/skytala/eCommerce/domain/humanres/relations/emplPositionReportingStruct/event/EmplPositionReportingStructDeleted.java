package com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.model.EmplPositionReportingStruct;
public class EmplPositionReportingStructDeleted implements Event{

	private boolean success;

	public EmplPositionReportingStructDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
