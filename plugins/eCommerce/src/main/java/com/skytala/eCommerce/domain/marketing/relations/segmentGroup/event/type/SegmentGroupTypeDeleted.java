package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type.SegmentGroupType;
public class SegmentGroupTypeDeleted implements Event{

	private boolean success;

	public SegmentGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
