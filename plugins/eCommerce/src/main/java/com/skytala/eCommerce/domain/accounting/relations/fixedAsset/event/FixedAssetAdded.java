package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.FixedAsset;
public class FixedAssetAdded implements Event{

	private FixedAsset addedFixedAsset;
	private boolean success;

	public FixedAssetAdded(FixedAsset addedFixedAsset, boolean success){
		this.addedFixedAsset = addedFixedAsset;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAsset getAddedFixedAsset() {
		return addedFixedAsset;
	}

}
