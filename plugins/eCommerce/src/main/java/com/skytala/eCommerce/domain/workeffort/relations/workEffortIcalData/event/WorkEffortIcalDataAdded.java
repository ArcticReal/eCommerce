package com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.model.WorkEffortIcalData;
public class WorkEffortIcalDataAdded implements Event{

	private WorkEffortIcalData addedWorkEffortIcalData;
	private boolean success;

	public WorkEffortIcalDataAdded(WorkEffortIcalData addedWorkEffortIcalData, boolean success){
		this.addedWorkEffortIcalData = addedWorkEffortIcalData;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortIcalData getAddedWorkEffortIcalData() {
		return addedWorkEffortIcalData;
	}

}
