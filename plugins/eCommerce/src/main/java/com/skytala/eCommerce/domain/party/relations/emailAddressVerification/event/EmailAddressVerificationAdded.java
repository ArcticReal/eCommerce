package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
public class EmailAddressVerificationAdded implements Event{

	private EmailAddressVerification addedEmailAddressVerification;
	private boolean success;

	public EmailAddressVerificationAdded(EmailAddressVerification addedEmailAddressVerification, boolean success){
		this.addedEmailAddressVerification = addedEmailAddressVerification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmailAddressVerification getAddedEmailAddressVerification() {
		return addedEmailAddressVerification;
	}

}
