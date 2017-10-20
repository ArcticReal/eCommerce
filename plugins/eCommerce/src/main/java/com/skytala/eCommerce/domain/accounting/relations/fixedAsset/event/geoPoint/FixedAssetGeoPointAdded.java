package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint.FixedAssetGeoPoint;
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
