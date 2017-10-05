package com.skytala.eCommerce.domain.fixedAssetIdentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;
public class FixedAssetIdentTypeAdded implements Event{

	private FixedAssetIdentType addedFixedAssetIdentType;
	private boolean success;

	public FixedAssetIdentTypeAdded(FixedAssetIdentType addedFixedAssetIdentType, boolean success){
		this.addedFixedAssetIdentType = addedFixedAssetIdentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetIdentType getAddedFixedAssetIdentType() {
		return addedFixedAssetIdentType;
	}

}
