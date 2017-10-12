package com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupGeo.model.SegmentGroupGeo;
public class SegmentGroupGeoDeleted implements Event{

	private boolean success;

	public SegmentGroupGeoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
