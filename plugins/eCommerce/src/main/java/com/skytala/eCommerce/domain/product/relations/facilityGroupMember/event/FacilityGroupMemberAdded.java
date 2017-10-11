package com.skytala.eCommerce.domain.product.relations.facilityGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.model.FacilityGroupMember;
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
