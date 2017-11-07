package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
public class X509IssuerProvisionDeleted implements Event{

	private boolean success;

	public X509IssuerProvisionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
