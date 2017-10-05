package com.skytala.eCommerce.domain.fixedAssetProductType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetProductType.model.FixedAssetProductType;
public class FixedAssetProductTypeDeleted implements Event{

	private boolean success;

	public FixedAssetProductTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
