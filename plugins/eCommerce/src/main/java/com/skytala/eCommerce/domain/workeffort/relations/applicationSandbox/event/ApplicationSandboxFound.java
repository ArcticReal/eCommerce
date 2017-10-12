package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
public class ApplicationSandboxFound implements Event{

	private List<ApplicationSandbox> applicationSandboxs;

	public ApplicationSandboxFound(List<ApplicationSandbox> applicationSandboxs) {
		this.applicationSandboxs = applicationSandboxs;
	}

	public List<ApplicationSandbox> getApplicationSandboxs()	{
		return applicationSandboxs;
	}

}
