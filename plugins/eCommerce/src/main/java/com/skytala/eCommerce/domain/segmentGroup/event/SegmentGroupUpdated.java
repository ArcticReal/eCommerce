package com.skytala.eCommerce.domain.segmentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.segmentGroup.model.SegmentGroup;
public class SegmentGroupUpdated implements Event{

	private boolean success;

	public SegmentGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
