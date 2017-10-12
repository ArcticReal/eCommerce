package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountUpdated implements Event{

	private boolean success;

	public FixedAssetTypeGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
