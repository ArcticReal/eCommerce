package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;
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
