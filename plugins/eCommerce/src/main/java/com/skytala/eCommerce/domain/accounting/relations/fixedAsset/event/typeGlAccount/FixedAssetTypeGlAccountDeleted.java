package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountDeleted implements Event{

	private boolean success;

	public FixedAssetTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
