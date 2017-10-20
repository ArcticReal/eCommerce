package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;
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
