package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;
public class JobRequisitionUpdated implements Event{

	private boolean success;

	public JobRequisitionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
