package com.skytala.eCommerce.domain.segmentGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.segmentGroupType.model.SegmentGroupType;
public class SegmentGroupTypeUpdated implements Event{

	private boolean success;

	public SegmentGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
