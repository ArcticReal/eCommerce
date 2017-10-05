package com.skytala.eCommerce.domain.fixedAssetIdentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;
public class FixedAssetIdentTypeUpdated implements Event{

	private boolean success;

	public FixedAssetIdentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
