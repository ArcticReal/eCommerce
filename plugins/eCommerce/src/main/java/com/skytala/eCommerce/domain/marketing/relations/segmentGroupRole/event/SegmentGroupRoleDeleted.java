package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
public class SegmentGroupRoleDeleted implements Event{

	private boolean success;

	public SegmentGroupRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
