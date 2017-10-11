package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
public class EmailAddressVerificationUpdated implements Event{

	private boolean success;

	public EmailAddressVerificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
