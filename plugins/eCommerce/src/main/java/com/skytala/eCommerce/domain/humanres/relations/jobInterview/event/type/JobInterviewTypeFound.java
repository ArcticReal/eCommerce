package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
public class JobInterviewTypeFound implements Event{

	private List<JobInterviewType> jobInterviewTypes;

	public JobInterviewTypeFound(List<JobInterviewType> jobInterviewTypes) {
		this.jobInterviewTypes = jobInterviewTypes;
	}

	public List<JobInterviewType> getJobInterviewTypes()	{
		return jobInterviewTypes;
	}

}
