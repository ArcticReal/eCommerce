package com.skytala.eCommerce.domain.jobInterviewType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobInterviewType.model.JobInterviewType;
public class JobInterviewTypeAdded implements Event{

	private JobInterviewType addedJobInterviewType;
	private boolean success;

	public JobInterviewTypeAdded(JobInterviewType addedJobInterviewType, boolean success){
		this.addedJobInterviewType = addedJobInterviewType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public JobInterviewType getAddedJobInterviewType() {
		return addedJobInterviewType;
	}

}
