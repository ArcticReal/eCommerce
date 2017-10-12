package com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.model.FixedAssetRegistration;
public class FixedAssetRegistrationDeleted implements Event{

	private boolean success;

	public FixedAssetRegistrationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
