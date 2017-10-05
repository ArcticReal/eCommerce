package com.skytala.eCommerce.domain.fixedAssetType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetType.model.FixedAssetType;
public class FixedAssetTypeUpdated implements Event{

	private boolean success;

	public FixedAssetTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
