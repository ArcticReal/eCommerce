package com.skytala.eCommerce.domain.fixedAssetStdCostType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetStdCostType.model.FixedAssetStdCostType;
public class FixedAssetStdCostTypeDeleted implements Event{

	private boolean success;

	public FixedAssetStdCostTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
