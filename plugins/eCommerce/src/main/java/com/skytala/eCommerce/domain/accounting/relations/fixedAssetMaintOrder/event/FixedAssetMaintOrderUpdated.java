package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model.FixedAssetMaintOrder;
public class FixedAssetMaintOrderUpdated implements Event{

	private boolean success;

	public FixedAssetMaintOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
