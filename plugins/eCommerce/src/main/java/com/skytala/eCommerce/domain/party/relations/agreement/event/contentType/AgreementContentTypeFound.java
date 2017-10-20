package com.skytala.eCommerce.domain.party.relations.agreement.event.contentType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.contentType.AgreementContentType;
public class AgreementContentTypeFound implements Event{

	private List<AgreementContentType> agreementContentTypes;

	public AgreementContentTypeFound(List<AgreementContentType> agreementContentTypes) {
		this.agreementContentTypes = agreementContentTypes;
	}

	public List<AgreementContentType> getAgreementContentTypes()	{
		return agreementContentTypes;
	}

}
