package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;
public class SegmentGroupRoleUpdated implements Event{

	private boolean success;

	public SegmentGroupRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
