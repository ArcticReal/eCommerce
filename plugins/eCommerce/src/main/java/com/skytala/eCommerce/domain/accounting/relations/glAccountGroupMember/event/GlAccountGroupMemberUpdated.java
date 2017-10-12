package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
public class GlAccountGroupMemberUpdated implements Event{

	private boolean success;

	public GlAccountGroupMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
