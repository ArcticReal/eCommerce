package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint.FixedAssetGeoPoint;
public class FixedAssetGeoPointUpdated implements Event{

	private boolean success;

	public FixedAssetGeoPointUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
