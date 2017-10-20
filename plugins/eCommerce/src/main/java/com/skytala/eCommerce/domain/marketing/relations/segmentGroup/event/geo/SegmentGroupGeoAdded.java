package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;
public class SegmentGroupGeoAdded implements Event{

	private SegmentGroupGeo addedSegmentGroupGeo;
	private boolean success;

	public SegmentGroupGeoAdded(SegmentGroupGeo addedSegmentGroupGeo, boolean success){
		this.addedSegmentGroupGeo = addedSegmentGroupGeo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SegmentGroupGeo getAddedSegmentGroupGeo() {
		return addedSegmentGroupGeo;
	}

}
