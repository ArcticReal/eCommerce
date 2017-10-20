package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;
public class FixedAssetMaintOrderUpdated implements Event{

	private boolean success;

	public FixedAssetMaintOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
