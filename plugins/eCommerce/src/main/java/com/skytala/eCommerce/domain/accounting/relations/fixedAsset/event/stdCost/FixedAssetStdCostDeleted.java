package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCost;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCost.FixedAssetStdCost;
public class FixedAssetStdCostDeleted implements Event{

	private boolean success;

	public FixedAssetStdCostDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
