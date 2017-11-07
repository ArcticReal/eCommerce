package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
public class X509IssuerProvisionAdded implements Event{

	private X509IssuerProvision addedX509IssuerProvision;
	private boolean success;

	public X509IssuerProvisionAdded(X509IssuerProvision addedX509IssuerProvision, boolean success){
		this.addedX509IssuerProvision = addedX509IssuerProvision;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public X509IssuerProvision getAddedX509IssuerProvision() {
		return addedX509IssuerProvision;
	}

}
