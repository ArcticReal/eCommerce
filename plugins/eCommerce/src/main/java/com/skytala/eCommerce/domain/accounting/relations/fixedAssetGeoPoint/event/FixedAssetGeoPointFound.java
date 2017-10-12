package com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetGeoPoint.model.FixedAssetGeoPoint;
public class FixedAssetGeoPointFound implements Event{

	private List<FixedAssetGeoPoint> fixedAssetGeoPoints;

	public FixedAssetGeoPointFound(List<FixedAssetGeoPoint> fixedAssetGeoPoints) {
		this.fixedAssetGeoPoints = fixedAssetGeoPoints;
	}

	public List<FixedAssetGeoPoint> getFixedAssetGeoPoints()	{
		return fixedAssetGeoPoints;
	}

}
