package com.skytala.eCommerce.domain.party.relations.agreement.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
public class AgreementContentFound implements Event{

	private List<AgreementContent> agreementContents;

	public AgreementContentFound(List<AgreementContent> agreementContents) {
		this.agreementContents = agreementContents;
	}

	public List<AgreementContent> getAgreementContents()	{
		return agreementContents;
	}

}
