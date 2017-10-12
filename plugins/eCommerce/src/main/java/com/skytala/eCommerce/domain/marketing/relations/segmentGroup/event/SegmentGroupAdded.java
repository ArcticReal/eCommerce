package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;
public class SegmentGroupAdded implements Event{

	private SegmentGroup addedSegmentGroup;
	private boolean success;

	public SegmentGroupAdded(SegmentGroup addedSegmentGroup, boolean success){
		this.addedSegmentGroup = addedSegmentGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SegmentGroup getAddedSegmentGroup() {
		return addedSegmentGroup;
	}

}
