package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration.FixedAssetRegistration;
public class FixedAssetRegistrationUpdated implements Event{

	private boolean success;

	public FixedAssetRegistrationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
