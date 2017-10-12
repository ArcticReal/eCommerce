package com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.model.WorkEffortIcalData;
public class WorkEffortIcalDataDeleted implements Event{

	private boolean success;

	public WorkEffortIcalDataDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
