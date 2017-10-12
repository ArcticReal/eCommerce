package com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.model.FixedAssetGeoPoint;
public class FixedAssetGeoPointAdded implements Event{

	private FixedAssetGeoPoint addedFixedAssetGeoPoint;
	private boolean success;

	public FixedAssetGeoPointAdded(FixedAssetGeoPoint addedFixedAssetGeoPoint, boolean success){
		this.addedFixedAssetGeoPoint = addedFixedAssetGeoPoint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetGeoPoint getAddedFixedAssetGeoPoint() {
		return addedFixedAssetGeoPoint;
	}

}
