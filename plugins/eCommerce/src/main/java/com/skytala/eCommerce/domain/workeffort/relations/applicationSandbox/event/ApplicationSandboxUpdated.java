package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
public class ApplicationSandboxUpdated implements Event{

	private boolean success;

	public ApplicationSandboxUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
