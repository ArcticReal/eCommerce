package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;
public class SegmentGroupGeoUpdated implements Event{

	private boolean success;

	public SegmentGroupGeoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
