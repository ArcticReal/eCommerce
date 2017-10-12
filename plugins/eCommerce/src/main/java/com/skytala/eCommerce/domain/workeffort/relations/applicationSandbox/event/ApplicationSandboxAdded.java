package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
public class ApplicationSandboxAdded implements Event{

	private ApplicationSandbox addedApplicationSandbox;
	private boolean success;

	public ApplicationSandboxAdded(ApplicationSandbox addedApplicationSandbox, boolean success){
		this.addedApplicationSandbox = addedApplicationSandbox;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ApplicationSandbox getAddedApplicationSandbox() {
		return addedApplicationSandbox;
	}

}
