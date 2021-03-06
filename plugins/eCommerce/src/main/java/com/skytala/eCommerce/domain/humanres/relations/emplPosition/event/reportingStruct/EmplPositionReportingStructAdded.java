package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;
public class EmplPositionReportingStructAdded implements Event{

	private EmplPositionReportingStruct addedEmplPositionReportingStruct;
	private boolean success;

	public EmplPositionReportingStructAdded(EmplPositionReportingStruct addedEmplPositionReportingStruct, boolean success){
		this.addedEmplPositionReportingStruct = addedEmplPositionReportingStruct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionReportingStruct getAddedEmplPositionReportingStruct() {
		return addedEmplPositionReportingStruct;
	}

}
