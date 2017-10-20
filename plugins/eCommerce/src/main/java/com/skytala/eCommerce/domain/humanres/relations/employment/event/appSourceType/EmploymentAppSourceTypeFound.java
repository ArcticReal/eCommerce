package com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType.EmploymentAppSourceType;
public class EmploymentAppSourceTypeFound implements Event{

	private List<EmploymentAppSourceType> employmentAppSourceTypes;

	public EmploymentAppSourceTypeFound(List<EmploymentAppSourceType> employmentAppSourceTypes) {
		this.employmentAppSourceTypes = employmentAppSourceTypes;
	}

	public List<EmploymentAppSourceType> getEmploymentAppSourceTypes()	{
		return employmentAppSourceTypes;
	}

}
