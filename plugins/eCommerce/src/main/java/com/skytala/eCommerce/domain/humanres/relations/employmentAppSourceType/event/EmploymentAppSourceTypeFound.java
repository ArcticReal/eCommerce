package com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.model.EmploymentAppSourceType;
public class EmploymentAppSourceTypeFound implements Event{

	private List<EmploymentAppSourceType> employmentAppSourceTypes;

	public EmploymentAppSourceTypeFound(List<EmploymentAppSourceType> employmentAppSourceTypes) {
		this.employmentAppSourceTypes = employmentAppSourceTypes;
	}

	public List<EmploymentAppSourceType> getEmploymentAppSourceTypes()	{
		return employmentAppSourceTypes;
	}

}
