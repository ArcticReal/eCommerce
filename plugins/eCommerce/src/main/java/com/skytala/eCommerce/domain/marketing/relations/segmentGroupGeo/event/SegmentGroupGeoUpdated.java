package com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.model.SegmentGroupGeo;
public class SegmentGroupGeoUpdated implements Event{

	private boolean success;

	public SegmentGroupGeoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
