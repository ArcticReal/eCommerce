package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;
public class SegmentGroupRoleDeleted implements Event{

	private boolean success;

	public SegmentGroupRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
