package com.skytala.eCommerce.domain.product.relations.facilityGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.model.FacilityGroupMember;
public class FacilityGroupMemberDeleted implements Event{

	private boolean success;

	public FacilityGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
