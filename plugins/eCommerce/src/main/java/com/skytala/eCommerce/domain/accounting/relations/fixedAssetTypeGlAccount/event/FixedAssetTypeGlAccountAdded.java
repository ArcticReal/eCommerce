package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountAdded implements Event{

	private FixedAssetTypeGlAccount addedFixedAssetTypeGlAccount;
	private boolean success;

	public FixedAssetTypeGlAccountAdded(FixedAssetTypeGlAccount addedFixedAssetTypeGlAccount, boolean success){
		this.addedFixedAssetTypeGlAccount = addedFixedAssetTypeGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetTypeGlAccount getAddedFixedAssetTypeGlAccount() {
		return addedFixedAssetTypeGlAccount;
	}

}
