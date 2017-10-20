package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;
public class FixedAssetMaintOrderDeleted implements Event{

	private boolean success;

	public FixedAssetMaintOrderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
