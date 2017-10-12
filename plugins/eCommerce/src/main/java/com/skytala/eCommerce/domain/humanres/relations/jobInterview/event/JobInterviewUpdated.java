package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.JobInterview;
public class JobInterviewUpdated implements Event{

	private boolean success;

	public JobInterviewUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
