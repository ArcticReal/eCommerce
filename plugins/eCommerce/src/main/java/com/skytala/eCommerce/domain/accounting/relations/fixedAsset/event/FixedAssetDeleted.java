package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.FixedAsset;
public class FixedAssetDeleted implements Event{

	private boolean success;

	public FixedAssetDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
