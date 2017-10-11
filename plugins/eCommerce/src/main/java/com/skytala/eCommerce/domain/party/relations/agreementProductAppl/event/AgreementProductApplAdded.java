package com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;
public class AgreementProductApplAdded implements Event{

	private AgreementProductAppl addedAgreementProductAppl;
	private boolean success;

	public AgreementProductApplAdded(AgreementProductAppl addedAgreementProductAppl, boolean success){
		this.addedAgreementProductAppl = addedAgreementProductAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementProductAppl getAddedAgreementProductAppl() {
		return addedAgreementProductAppl;
	}

}
