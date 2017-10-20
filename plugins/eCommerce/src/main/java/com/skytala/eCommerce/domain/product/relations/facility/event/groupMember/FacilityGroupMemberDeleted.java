package com.skytala.eCommerce.domain.product.relations.facility.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
public class FacilityGroupMemberDeleted implements Event{

	private boolean success;

	public FacilityGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
