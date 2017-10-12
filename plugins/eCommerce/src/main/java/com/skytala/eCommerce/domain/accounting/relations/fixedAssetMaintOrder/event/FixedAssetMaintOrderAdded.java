package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model.FixedAssetMaintOrder;
public class FixedAssetMaintOrderAdded implements Event{

	private FixedAssetMaintOrder addedFixedAssetMaintOrder;
	private boolean success;

	public FixedAssetMaintOrderAdded(FixedAssetMaintOrder addedFixedAssetMaintOrder, boolean success){
		this.addedFixedAssetMaintOrder = addedFixedAssetMaintOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetMaintOrder getAddedFixedAssetMaintOrder() {
		return addedFixedAssetMaintOrder;
	}

}
