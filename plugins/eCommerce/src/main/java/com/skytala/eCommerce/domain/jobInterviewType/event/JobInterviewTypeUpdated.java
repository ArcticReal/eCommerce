package com.skytala.eCommerce.domain.jobInterviewType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobInterviewType.model.JobInterviewType;
public class JobInterviewTypeUpdated implements Event{

	private boolean success;

	public JobInterviewTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
