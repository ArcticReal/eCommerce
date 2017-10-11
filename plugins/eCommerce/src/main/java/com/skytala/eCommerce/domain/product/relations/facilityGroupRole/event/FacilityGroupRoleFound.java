package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;
public class FacilityGroupRoleFound implements Event{

	private List<FacilityGroupRole> facilityGroupRoles;

	public FacilityGroupRoleFound(List<FacilityGroupRole> facilityGroupRoles) {
		this.facilityGroupRoles = facilityGroupRoles;
	}

	public List<FacilityGroupRole> getFacilityGroupRoles()	{
		return facilityGroupRoles;
	}

}
