package com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;
public class AgreementPromoApplFound implements Event{

	private List<AgreementPromoAppl> agreementPromoAppls;

	public AgreementPromoApplFound(List<AgreementPromoAppl> agreementPromoAppls) {
		this.agreementPromoAppls = agreementPromoAppls;
	}

	public List<AgreementPromoAppl> getAgreementPromoAppls()	{
		return agreementPromoAppls;
	}

}
