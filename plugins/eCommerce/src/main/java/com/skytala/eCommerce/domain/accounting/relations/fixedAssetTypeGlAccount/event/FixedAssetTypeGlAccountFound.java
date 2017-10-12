package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeGlAccount.model.FixedAssetTypeGlAccount;
public class FixedAssetTypeGlAccountFound implements Event{

	private List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts;

	public FixedAssetTypeGlAccountFound(List<FixedAssetTypeGlAccount> fixedAssetTypeGlAccounts) {
		this.fixedAssetTypeGlAccounts = fixedAssetTypeGlAccounts;
	}

	public List<FixedAssetTypeGlAccount> getFixedAssetTypeGlAccounts()	{
		return fixedAssetTypeGlAccounts;
	}

}
