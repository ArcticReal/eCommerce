package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
public class JobInterviewTypeDeleted implements Event{

	private boolean success;

	public JobInterviewTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
