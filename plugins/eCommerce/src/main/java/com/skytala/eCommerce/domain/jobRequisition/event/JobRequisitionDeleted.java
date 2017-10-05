package com.skytala.eCommerce.domain.jobRequisition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobRequisition.model.JobRequisition;
public class JobRequisitionDeleted implements Event{

	private boolean success;

	public JobRequisitionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
