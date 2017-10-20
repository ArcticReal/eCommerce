package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
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
