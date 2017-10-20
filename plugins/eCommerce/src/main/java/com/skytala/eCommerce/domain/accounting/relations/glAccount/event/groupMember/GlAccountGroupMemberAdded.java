package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;
public class GlAccountGroupMemberAdded implements Event{

	private GlAccountGroupMember addedGlAccountGroupMember;
	private boolean success;

	public GlAccountGroupMemberAdded(GlAccountGroupMember addedGlAccountGroupMember, boolean success){
		this.addedGlAccountGroupMember = addedGlAccountGroupMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountGroupMember getAddedGlAccountGroupMember() {
		return addedGlAccountGroupMember;
	}

}
