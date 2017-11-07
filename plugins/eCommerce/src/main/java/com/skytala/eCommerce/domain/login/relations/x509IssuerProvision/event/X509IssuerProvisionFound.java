package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
public class X509IssuerProvisionFound implements Event{

	private List<X509IssuerProvision> x509IssuerProvisions;

	public X509IssuerProvisionFound(List<X509IssuerProvision> x509IssuerProvisions) {
		this.x509IssuerProvisions = x509IssuerProvisions;
	}

	public List<X509IssuerProvision> getX509IssuerProvisions()	{
		return x509IssuerProvisions;
	}

}
