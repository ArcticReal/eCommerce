package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
public class SegmentGroupRoleUpdated implements Event{

	private boolean success;

	public SegmentGroupRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
