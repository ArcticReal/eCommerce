package com.skytala.eCommerce.domain.jobRequisition.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobRequisition.model.JobRequisition;
public class JobRequisitionAdded implements Event{

	private JobRequisition addedJobRequisition;
	private boolean success;

	public JobRequisitionAdded(JobRequisition addedJobRequisition, boolean success){
		this.addedJobRequisition = addedJobRequisition;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public JobRequisition getAddedJobRequisition() {
		return addedJobRequisition;
	}

}
