package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountFound implements Event{

	private List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts;

	public FixedAssetTypeGlAccountFound(List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts) {
		this.fixedAssetTypeGlAccounts = fixedAssetTypeGlAccounts;
	}

	public List<FixedAssetTypeGlAccount> getFixedAssetTypeGlAccounts()	{
		return fixedAssetTypeGlAccounts;
	}

}
