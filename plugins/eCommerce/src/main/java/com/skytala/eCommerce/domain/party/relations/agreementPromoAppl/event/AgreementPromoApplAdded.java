package com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementPromoAppl.model.AgreementPromoAppl;
public class AgreementPromoApplAdded implements Event{

	private AgreementPromoAppl addedAgreementPromoAppl;
	private boolean success;

	public AgreementPromoApplAdded(AgreementPromoAppl addedAgreementPromoAppl, boolean success){
		this.addedAgreementPromoAppl = addedAgreementPromoAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementPromoAppl getAddedAgreementPromoAppl() {
		return addedAgreementPromoAppl;
	}

}
