package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
public class FixedAssetIdentDeleted implements Event{

	private boolean success;

	public FixedAssetIdentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
