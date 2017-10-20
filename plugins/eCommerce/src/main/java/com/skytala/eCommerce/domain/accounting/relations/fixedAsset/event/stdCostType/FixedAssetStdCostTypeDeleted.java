package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.stdCostType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.stdCostType.FixedAssetStdCostType;
public class FixedAssetStdCostTypeDeleted implements Event{

	private boolean success;

	public FixedAssetStdCostTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
