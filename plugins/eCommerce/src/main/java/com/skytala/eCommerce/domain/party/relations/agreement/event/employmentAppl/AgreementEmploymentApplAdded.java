package com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;
public class AgreementEmploymentApplAdded implements Event{

	private AgreementEmploymentAppl addedAgreementEmploymentAppl;
	private boolean success;

	public AgreementEmploymentApplAdded(AgreementEmploymentAppl addedAgreementEmploymentAppl, boolean success){
		this.addedAgreementEmploymentAppl = addedAgreementEmploymentAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementEmploymentAppl getAddedAgreementEmploymentAppl() {
		return addedAgreementEmploymentAppl;
	}

}
