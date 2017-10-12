package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;
public class JobRequisitionFound implements Event{

	private List<JobRequisition> jobRequisitions;

	public JobRequisitionFound(List<JobRequisition> jobRequisitions) {
		this.jobRequisitions = jobRequisitions;
	}

	public List<JobRequisition> getJobRequisitions()	{
		return jobRequisitions;
	}

}
