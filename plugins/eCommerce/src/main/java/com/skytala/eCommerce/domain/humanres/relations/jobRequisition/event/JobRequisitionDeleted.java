package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;
public class JobRequisitionDeleted implements Event{

	private boolean success;

	public JobRequisitionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
