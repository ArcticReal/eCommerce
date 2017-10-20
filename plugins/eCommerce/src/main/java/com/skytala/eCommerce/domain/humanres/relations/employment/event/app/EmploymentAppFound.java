package com.skytala.eCommerce.domain.humanres.relations.employment.event.app;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.app.EmploymentApp;
public class EmploymentAppFound implements Event{

	private List<EmploymentApp> employmentApps;

	public EmploymentAppFound(List<EmploymentApp> employmentApps) {
		this.employmentApps = employmentApps;
	}

	public List<EmploymentApp> getEmploymentApps()	{
		return employmentApps;
	}

}
