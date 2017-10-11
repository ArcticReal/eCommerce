package com.skytala.eCommerce.domain.product.relations.facilityGroupMember.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.model.FacilityGroupMember;
public class FacilityGroupMemberFound implements Event{

	private List<FacilityGroupMember> facilityGroupMembers;

	public FacilityGroupMemberFound(List<FacilityGroupMember> facilityGroupMembers) {
		this.facilityGroupMembers = facilityGroupMembers;
	}

	public List<FacilityGroupMember> getFacilityGroupMembers()	{
		return facilityGroupMembers;
	}

}
