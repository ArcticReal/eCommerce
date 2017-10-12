package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
public class SegmentGroupRoleFound implements Event{

	private List<SegmentGroupRole> segmentGroupRoles;

	public SegmentGroupRoleFound(List<SegmentGroupRole> segmentGroupRoles) {
		this.segmentGroupRoles = segmentGroupRoles;
	}

	public List<SegmentGroupRole> getSegmentGroupRoles()	{
		return segmentGroupRoles;
	}

}
