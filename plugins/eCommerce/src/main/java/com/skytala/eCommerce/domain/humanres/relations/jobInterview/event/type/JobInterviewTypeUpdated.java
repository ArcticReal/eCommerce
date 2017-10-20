package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
public class JobInterviewTypeUpdated implements Event{

	private boolean success;

	public JobInterviewTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
