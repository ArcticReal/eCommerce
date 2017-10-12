package com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.model.FixedAssetGeoPoint;
public class FixedAssetGeoPointUpdated implements Event{

	private boolean success;

	public FixedAssetGeoPointUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
