package com.skytala.eCommerce.domain.jobInterviewType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobInterviewType.model.JobInterviewType;
public class JobInterviewTypeFound implements Event{

	private List<JobInterviewType> jobInterviewTypes;

	public JobInterviewTypeFound(List<JobInterviewType> jobInterviewTypes) {
		this.jobInterviewTypes = jobInterviewTypes;
	}

	public List<JobInterviewType> getJobInterviewTypes()	{
		return jobInterviewTypes;
	}

}
