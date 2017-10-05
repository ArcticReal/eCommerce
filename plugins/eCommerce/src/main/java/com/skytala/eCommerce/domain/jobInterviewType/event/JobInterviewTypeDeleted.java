package com.skytala.eCommerce.domain.jobInterviewType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobInterviewType.model.JobInterviewType;
public class JobInterviewTypeDeleted implements Event{

	private boolean success;

	public JobInterviewTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
