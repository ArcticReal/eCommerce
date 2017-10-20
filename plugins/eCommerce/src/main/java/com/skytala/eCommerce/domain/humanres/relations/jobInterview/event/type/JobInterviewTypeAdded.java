package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
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
