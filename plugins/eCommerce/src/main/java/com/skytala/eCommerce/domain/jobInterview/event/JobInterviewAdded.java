package com.skytala.eCommerce.domain.jobInterview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobInterview.model.JobInterview;
public class JobInterviewAdded implements Event{

	private JobInterview addedJobInterview;
	private boolean success;

	public JobInterviewAdded(JobInterview addedJobInterview, boolean success){
		this.addedJobInterview = addedJobInterview;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public JobInterview getAddedJobInterview() {
		return addedJobInterview;
	}

}
