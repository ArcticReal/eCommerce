package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
public class GlAccountGroupMemberDeleted implements Event{

	private boolean success;

	public GlAccountGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
