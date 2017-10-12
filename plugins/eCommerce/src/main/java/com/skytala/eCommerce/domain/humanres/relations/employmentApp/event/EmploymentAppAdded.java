package com.skytala.eCommerce.domain.humanres.relations.employmentApp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;
public class EmploymentAppAdded implements Event{

	private EmploymentApp addedEmploymentApp;
	private boolean success;

	public EmploymentAppAdded(EmploymentApp addedEmploymentApp, boolean success){
		this.addedEmploymentApp = addedEmploymentApp;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmploymentApp getAddedEmploymentApp() {
		return addedEmploymentApp;
	}

}
