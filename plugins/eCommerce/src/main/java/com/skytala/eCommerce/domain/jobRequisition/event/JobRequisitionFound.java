package com.skytala.eCommerce.domain.jobRequisition.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.jobRequisition.model.JobRequisition;
public class JobRequisitionFound implements Event{

	private List<JobRequisition> jobRequisitions;

	public JobRequisitionFound(List<JobRequisition> jobRequisitions) {
		this.jobRequisitions = jobRequisitions;
	}

	public List<JobRequisition> getJobRequisitions()	{
		return jobRequisitions;
	}

}
