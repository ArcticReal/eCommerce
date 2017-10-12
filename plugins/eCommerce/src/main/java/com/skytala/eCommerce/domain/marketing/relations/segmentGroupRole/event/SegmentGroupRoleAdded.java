package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
public class SegmentGroupRoleAdded implements Event{

	private SegmentGroupRole addedSegmentGroupRole;
	private boolean success;

	public SegmentGroupRoleAdded(SegmentGroupRole addedSegmentGroupRole, boolean success){
		this.addedSegmentGroupRole = addedSegmentGroupRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SegmentGroupRole getAddedSegmentGroupRole() {
		return addedSegmentGroupRole;
	}

}
