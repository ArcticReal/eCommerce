package com.skytala.eCommerce.domain.humanres.relations.employmentApp.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;
public class EmploymentAppFound implements Event{

	private List<EmploymentApp> employmentApps;

	public EmploymentAppFound(List<EmploymentApp> employmentApps) {
		this.employmentApps = employmentApps;
	}

	public List<EmploymentApp> getEmploymentApps()	{
		return employmentApps;
	}

}
