package com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetStdCost.model.FixedAssetStdCost;
public class FixedAssetStdCostDeleted implements Event{

	private boolean success;

	public FixedAssetStdCostDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
