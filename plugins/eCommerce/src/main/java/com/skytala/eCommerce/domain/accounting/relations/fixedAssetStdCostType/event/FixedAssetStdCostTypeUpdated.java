package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCostType.model.FixedAssetStdCostType;
public class FixedAssetStdCostTypeUpdated implements Event{

	private boolean success;

	public FixedAssetStdCostTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
