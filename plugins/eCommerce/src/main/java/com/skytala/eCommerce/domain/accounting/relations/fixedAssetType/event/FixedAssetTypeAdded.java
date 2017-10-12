package com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.model.FixedAssetType;
public class FixedAssetTypeAdded implements Event{

	private FixedAssetType addedFixedAssetType;
	private boolean success;

	public FixedAssetTypeAdded(FixedAssetType addedFixedAssetType, boolean success){
		this.addedFixedAssetType = addedFixedAssetType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetType getAddedFixedAssetType() {
		return addedFixedAssetType;
	}

}
