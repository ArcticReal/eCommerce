package com.skytala.eCommerce.domain.agreement.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.agreement.model.Agreement;
public class AgreementFound implements Event{

	private List<Agreement> agreements;

	public AgreementFound(List<Agreement> agreements) {
		this.agreements = agreements;
	}

	public List<Agreement> getAgreements()	{
		return agreements;
	}

}
