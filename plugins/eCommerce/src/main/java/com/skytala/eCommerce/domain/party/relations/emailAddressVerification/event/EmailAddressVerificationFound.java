package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
public class EmailAddressVerificationFound implements Event{

	private List<EmailAddressVerification> emailAddressVerifications;

	public EmailAddressVerificationFound(List<EmailAddressVerification> emailAddressVerifications) {
		this.emailAddressVerifications = emailAddressVerifications;
	}

	public List<EmailAddressVerification> getEmailAddressVerifications()	{
		return emailAddressVerifications;
	}

}
