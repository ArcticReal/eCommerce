package com.skytala.eCommerce.domain.humanres.relations.jobInterview.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.JobInterview;
public class JobInterviewFound implements Event{

	private List<JobInterview> jobInterviews;

	public JobInterviewFound(List<JobInterview> jobInterviews) {
		this.jobInterviews = jobInterviews;
	}

	public List<JobInterview> getJobInterviews()	{
		return jobInterviews;
	}

}
