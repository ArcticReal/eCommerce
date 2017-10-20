package com.skytala.eCommerce.domain.product.relations.facility.event.groupMember;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
public class FacilityGroupMemberFound implements Event{

	private List<FacilityGroupMember> facilityGroupMembers;

	public FacilityGroupMemberFound(List<FacilityGroupMember> facilityGroupMembers) {
		this.facilityGroupMembers = facilityGroupMembers;
	}

	public List<FacilityGroupMember> getFacilityGroupMembers()	{
		return facilityGroupMembers;
	}

}
