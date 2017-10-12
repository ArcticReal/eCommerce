package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;
public class SegmentGroupDeleted implements Event{

	private boolean success;

	public SegmentGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
