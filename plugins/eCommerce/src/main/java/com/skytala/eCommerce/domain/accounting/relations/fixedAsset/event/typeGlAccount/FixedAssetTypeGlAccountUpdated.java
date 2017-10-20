package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountUpdated implements Event{

	private boolean success;

	public FixedAssetTypeGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
