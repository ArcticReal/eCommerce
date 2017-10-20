package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;
public class SegmentGroupRoleFound implements Event{

	private List<SegmentGroupRole> segmentGroupRoles;

	public SegmentGroupRoleFound(List<SegmentGroupRole> segmentGroupRoles) {
		this.segmentGroupRoles = segmentGroupRoles;
	}

	public List<SegmentGroupRole> getSegmentGroupRoles()	{
		return segmentGroupRoles;
	}

}
