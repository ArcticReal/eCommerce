package com.skytala.eCommerce.domain.product.relations.facility.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
public class FacilityGroupMemberAdded implements Event{

	private FacilityGroupMember addedFacilityGroupMember;
	private boolean success;

	public FacilityGroupMemberAdded(FacilityGroupMember addedFacilityGroupMember, boolean success){
		this.addedFacilityGroupMember = addedFacilityGroupMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityGroupMember getAddedFacilityGroupMember() {
		return addedFacilityGroupMember;
	}

}
