package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.type.FixedAssetType;
public class FixedAssetTypeUpdated implements Event{

	private boolean success;

	public FixedAssetTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
