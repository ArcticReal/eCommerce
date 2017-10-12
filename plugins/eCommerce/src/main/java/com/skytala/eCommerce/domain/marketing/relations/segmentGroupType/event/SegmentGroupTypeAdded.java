package com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupType.model.SegmentGroupType;
public class SegmentGroupTypeAdded implements Event{

	private SegmentGroupType addedSegmentGroupType;
	private boolean success;

	public SegmentGroupTypeAdded(SegmentGroupType addedSegmentGroupType, boolean success){
		this.addedSegmentGroupType = addedSegmentGroupType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SegmentGroupType getAddedSegmentGroupType() {
		return addedSegmentGroupType;
	}

}
