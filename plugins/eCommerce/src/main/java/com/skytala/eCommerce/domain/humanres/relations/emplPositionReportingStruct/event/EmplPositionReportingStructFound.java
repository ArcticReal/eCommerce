package com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.model.EmplPositionReportingStruct;
public class EmplPositionReportingStructFound implements Event{

	private List<EmplPositionReportingStruct> emplPositionReportingStructs;

	public EmplPositionReportingStructFound(List<EmplPositionReportingStruct> emplPositionReportingStructs) {
		this.emplPositionReportingStructs = emplPositionReportingStructs;
	}

	public List<EmplPositionReportingStruct> getEmplPositionReportingStructs()	{
		return emplPositionReportingStructs;
	}

}
