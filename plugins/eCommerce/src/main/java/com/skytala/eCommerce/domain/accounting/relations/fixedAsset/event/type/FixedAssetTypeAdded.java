package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.type.FixedAssetType;
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
